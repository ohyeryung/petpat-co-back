package com.smile.petpat.image.domain;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.smile.petpat.image.repository.ImageRepository;
import com.smile.petpat.post.category.domain.PostType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor    // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Component
@Service
public class S3Uploader {
    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;
    private final ImageUtils imageUtils;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 이미지 파일 업로드
    @Transactional
    public List<String> uploadFile(List<MultipartFile> multipartFiles, Long postId, PostType postType) {
        try {
            List<String> imageUrlList = new ArrayList<>();

            List<Image> imageList = new ArrayList<>();

            // forEach 구문을 통해 multipartFile로 넘어온 파일들 하나씩 fileNameList에 추가
            multipartFiles.forEach(file -> {

                String fakeFileName = imageUtils.createFileName(file.getOriginalFilename());

                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(file.getSize());
                objectMetadata.setContentType(file.getContentType());

                try (InputStream inputStream = file.getInputStream()) {
                    amazonS3.putObject(new PutObjectRequest(bucket, fakeFileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
                } catch (IOException e) {
                     throw new CustomException(FAILED_UPLOAD_IMAGE);
                }
                String originalFileName = file.getOriginalFilename();
                String filePath = amazonS3.getUrl(bucket, fakeFileName).toString();

                Image image = new Image(originalFileName, fakeFileName, filePath, postType, postId);
                imageRepository.save(image);

               

                imageList.add(image);

                imageUrlList.add(filePath);
            });
            imageRepository.saveAll(imageList);
            return imageUrlList;
        } catch (NullPointerException e) {
            List<String> imageUrlList = new ArrayList<>();
            imageUrlList.add("null");
            return imageUrlList;
        }
    }


    // 이미지 파일 수정
    @Transactional
    public List<String> updateFile(List<MultipartFile> multipartFiles, Long postId, PostType postType) {
        for (String key : createKey(postId, postType)) getFileExtension(key);
        deleteS3(postId, postType);
        deleteImg(postId, postType);
        return uploadFile(multipartFiles, postId, postType);
    }

    // 게시글 Id와 postType 으로 S3 이미지 삭제
    @Transactional
    public void deleteS3(Long postId, PostType postType) {
        List<String> imgUrl = createKey(postId, postType);
        for (String img : imgUrl) {
            deleteImage(img);
        }
    }

    // 로컬 이미지 삭제
    public void deleteImg(Long postId, PostType postType) {
        imageRepository.deleteByPostIdAndPostType(postId, postType);
    }

    // S3 이미지 삭제
    public void deleteImage(String fileName){
        amazonS3.deleteObject(bucket, fileName);
    }

    // 게시글 이미지 url 리스트 추출 method
    @Transactional
    public List<String> createImgList(Long postId, PostType postType) {
        List<Image> images = imageRepository.findAllByPostIdAndPostType(postId, postType);
        return images.stream().map(Image::getFilePath).collect(Collectors.toList());
    }

    // 게시글 별 이미지 key 값 추출 메소드
    @Transactional
    public List<String> createKey(Long postId, PostType postType) {
        List<Image> images = imageRepository.findAllByPostIdAndPostType(postId, postType);
        return images.stream().map(Image::getFakeFileName).collect(Collectors.toList());
    }

    // 파일명 난수화
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        try {
            int idx = fileName.lastIndexOf(".");
            String fileExtension = fileName.substring(idx+1);
            // 파일 확장자 체크로직
            switch (fileExtension) {
                case "gif" : break;
                case "png" : break;
                case "jpg" : break;
                case "jpge" : break;
                default: throw new IllegalArgumentException("파일 타입을 확인해주세요");
            }
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("파일 타입을 확인해주세요");
        }

    }


}