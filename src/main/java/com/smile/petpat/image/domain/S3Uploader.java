package com.smile.petpat.image.domain;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.smile.petpat.common.exception.CustomException;
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
    private final ImageUtils imageUtils;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<String> uploadFile(List<MultipartFile> multipartFiles) {
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
                Image image = new Image(originalFileName, fakeFileName, filePath);

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



}