package com.smile.petpat.image.domain;

import com.smile.petpat.post.category.domain.PostType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageUploadManager {

    private final S3Uploader s3Uploader;
    private final ImageUploader imageUploader;
    private final ImageUtils imageUtils;

    /** User 프로필 등록 */
    public String saveProfileImage(MultipartFile multipartFile,String originImgPath) {
        String fakeFileName = imageUtils.generateRandomFileName(multipartFile.getOriginalFilename());
//        String originFileName = multipartFile.getOriginalFilename();
//        String filepath = s3Uploader.uploadFile(multipartFile);

        //기존 프로필 이미지 삭제
        if(originImgPath!="") {  //기존 프로필 이미지가 있는 경우
            s3Uploader.deleteImage(originImgPath);
        }
        return s3Uploader.uploadFile(multipartFile, fakeFileName);
    }

    /* 게시글 이미지 등록 (대표이미지 설정) */
    @Transactional
    public void uploadPostImage(List<MultipartFile> multipartFiles, Long postId, PostType postType) {
        List<Image> imageList = new ArrayList<>();
        for (int i = 0; i < multipartFiles.size(); i++) {
            String fakeFileName = imageUtils.generateRandomFileName(multipartFiles.get(i).getOriginalFilename());
            String originalFileName = multipartFiles.get(i).getOriginalFilename();
            String filePath = s3Uploader.uploadFile(multipartFiles.get(i), fakeFileName);

            //이미지 리스트의 순서에 따라 이미지의 우선순위 결정
            ImagePriority priority = ImagePriority.fromIndexToPriority(i+1);

            Image image = imageUploader.toImageEntity(originalFileName, fakeFileName,  filePath, postId, postType,priority);
            imageList.add(image);
        }
        imageUploader.savePostImage(imageList);
    }

    /* 이미지 파일 수정 */
    public void updateImage(List<MultipartFile> multipartFiles,Long postId, PostType postType) {
        List<String> fakeFiles = imageUploader.createKey(postId, postType);
        s3Uploader.deleteS3(fakeFiles, postId, postType);
        imageUploader.deleteImg(postId, postType);
        uploadPostImage(multipartFiles, postId, postType);
    }

    /** 이미지 파일 수정 - 대표이미지 삭제 & 우선순위 삽입 버전 */
    @Transactional
    public void updateImageNew(List<MultipartFile> newImages,List<String> deletedImgUrls,Long postId, PostType postType) {
        //삭제되거나 추가되는 이미지가 없으면 return
        if(newImages.size()==0 && deletedImgUrls.size()==0) return;

        //삭제되는 이미지 삭제
        for(String deletedImgUrl : deletedImgUrls){
            s3Uploader.deleteImage(imageUploader.getFakeFileNameByImageUrl(deletedImgUrl));
            imageUploader.deleteImgByImgUrl(deletedImgUrl);
        }

        //기존의 ImageList 에 추가되는 image 삽입
        List<Image> imageList = imageUploader.getImagesByPostTypeAndPostId(postType,postId);
        for(MultipartFile newImage : newImages){
            String fakeFileName = imageUtils.generateRandomFileName(newImage.getOriginalFilename());
            String originalFileName = newImage.getOriginalFilename();
            String filePath = s3Uploader.uploadFile(newImage, fakeFileName);

            Image image = imageUploader.toImageEntity(originalFileName, fakeFileName, filePath, postId, postType);
            imageList.add(image);
        }

        //ImageList 의 배열순서에 따라 우선순위 부여
        ImagePriority.setPriority(imageList);
        imageUploader.savePostImage(imageList);
    }

    /* 게시글 삭제 이미지도 삭제 */
    public void removePostImage(Long postId, PostType postType) {
        List<String> fakeFiles = imageUploader.createKey(postId, postType);
        s3Uploader.deleteS3(fakeFiles, postId, postType);
        imageUploader.deleteImg(postId, postType);
    }
}
