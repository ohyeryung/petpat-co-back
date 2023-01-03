package com.smile.petpat.image.domain;

import com.smile.petpat.post.category.domain.PostType;
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

    /* 게시글 이미지 등록 */
    public List<String> uploadPostImage(List<MultipartFile> multipartFiles, Long postId, PostType postType){
        List<Image> imageList = new ArrayList<>();
        multipartFiles.forEach(file ->{
            String fakeFileName = imageUtils.createFileName(file.getOriginalFilename());
            String originalFileName = file.getOriginalFilename();
            String filePath = s3Uploader.uploadFile(file);
            Image image = imageUploader.toImageEntity(fakeFileName,originalFileName,filePath,postId,postType);
            imageList.add(image);
        });

        return imageUploader.savePostImage(imageList).stream()
                .map(Image::getFilePath)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // 이미지 파일 수정
    public List<String> updateImage(List<MultipartFile> multipartFiles, Long postId, PostType postType) {
        List<String> fakeFiles = imageUploader.createKey(postId, postType);
        s3Uploader.deleteS3(fakeFiles,postId, postType);
        imageUploader.deleteImg(postId, postType);
        return uploadPostImage(multipartFiles, postId, postType);
    }

    // 게시글 삭제 이미지도 삭제
    public void removePostImage( Long postId, PostType postType){
        List<String> fakeFiles = imageUploader.createKey(postId, postType);
        s3Uploader.deleteS3(fakeFiles,postId, postType);
        imageUploader.deleteImg(postId, postType);

    }
}
