package com.smile.petpat.image.domain;

import com.amazonaws.services.s3.AmazonS3;
import com.smile.petpat.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ImageUploadManager {

    private final S3Uploader s3Uploader;
    private final ImageUploader imageUploader;
    private final ImageUtils imageUtils;

    /* User 프로필 등록 */
    public void saveProfileImage(){

    }

    /*게시글 이미지 등록 */
    public List<String> uploadPostImage(List<MultipartFile> multipartFiles){
        List<Image> imageList = new ArrayList<>();
        multipartFiles.forEach(file ->{
            String fakeFileName = imageUtils.createFileName(file.getOriginalFilename());
            String originalFileName = file.getOriginalFilename();
            String filePath = s3Uploader.uploadFile(file);
            Image image = imageUploader.toImageEntity(fakeFileName,originalFileName,filePath);
            imageList.add(image);
        });

        return imageUploader.savePostImage(imageList).stream()
                .map(image -> image.getFilePath())
                .collect(Collectors.toCollection(ArrayList :: new));
    }






}
