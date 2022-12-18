package com.smile.petpat.image.domain;

import com.smile.petpat.post.category.domain.PostType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageUploader {
    private final S3Uploader s3Uploader;

    public void saveFile(List< MultipartFile > multipartFiles, Long postId, PostType postType) {
        s3Uploader.uploadFile(multipartFiles, postId, postType);
    }
}
