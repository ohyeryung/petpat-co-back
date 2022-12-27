package com.smile.petpat.image.domain;

import com.smile.petpat.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageUploader {
    private final ImageRepository imageRepository;

    @Transactional
    public List<Image> savePostImage(List<Image> imageList){
        return imageRepository.saveAll(imageList);
    }

    public Image toImageEntity(String fakeFileName, String originalFileName, String filePath){
        return new Image(originalFileName,fakeFileName,filePath);
    }
}
