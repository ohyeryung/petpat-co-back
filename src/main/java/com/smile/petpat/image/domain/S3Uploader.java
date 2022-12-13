package com.smile.petpat.image.domain;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.smile.petpat.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.smile.petpat.common.response.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor    // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Component
@Service
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;
    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<String> uploadFile(List<MultipartFile> multipartFiles) {
        try {
            List<String> imageUrlList = new ArrayList<>();

            // forEach 구문을 통해 multipartFile로 넘어온 파일들 하나씩 fileNameList에 추가
            multipartFiles.forEach(file -> {
                String fakeFileName = createFileName(file.getOriginalFilename());
                String originalFileName = file.getOriginalFilename();
                // String fileFormatName = file.getContentType().substring(file.getContentType().lastIndexOf("/") + 1);
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(file.getSize());
                objectMetadata.setContentType(file.getContentType());
                try (InputStream inputStream = file.getInputStream()) {
                    amazonS3.putObject(new PutObjectRequest(bucket, fakeFileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
                } catch (IOException e) {
                    // throw new CustomException(FAILED_UPLOAD_IMAGE);
                    throw new IllegalArgumentException("파일 업로드에 실패했습니다.");
                }
                String filePath = amazonS3.getUrl(bucket, fakeFileName).toString();
                Image image = new Image(originalFileName, fakeFileName, filePath);
                imageRepository.save(image);
                imageUrlList.add(filePath);
            });
            return imageUrlList;

        } catch (NullPointerException e) {
            List<String> imageUrlList = new ArrayList<>();
            imageUrlList.add("null");
            return imageUrlList;
        }
    }

    // 파일명 난수화
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
//            throw new CustomException(WRONG_TYPE_IMAGE);
            throw new IllegalArgumentException("파일 타입을 확인해주세요");
        }

    }

}