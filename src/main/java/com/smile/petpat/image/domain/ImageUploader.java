package com.smile.petpat.image.domain;

import com.smile.petpat.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageUploader {
    private final ImageReader imageReader;
    // private final ImageStore imageStore;
    private final S3Uploader s3Uploader;
    private final ImageRepository imageRepository;
    private final Image image;

    public void saveFile(List< MultipartFile > multipartFiles) {
        s3Uploader.uploadFile(multipartFiles);
        imageRepository.save(image);
    }
}
