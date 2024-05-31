package com.smile.petpat.image.util;

import com.smile.petpat.image.domain.Image;
import com.smile.petpat.image.domain.ImagePriority;
import com.smile.petpat.image.service.ImageService;
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
    private final ImageService imageService;
    private final ImageUtils imageUtils;

    /** User 프로필 등록 */
    public String saveProfileImage(MultipartFile multipartFile,String originImgPath) {
        String fakeFileName = imageUtils.generateRandomFileName(multipartFile.getOriginalFilename());

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
        for (MultipartFile multipartFile : multipartFiles) {
            imageList.add(imageUtils.toImageEntity(postId, postType, multipartFile));
        }

        //ImageList 의 배열순서에 따라 우선순위 부여
        imageUtils.setPriority(imageList);
        imageService.savePostImage(imageList);
    }



    /* 이미지 파일 수정 - 대표이미지 삭제 & 우선순위 삽입 */
    @Transactional
    public void updateImage(List<MultipartFile> newImages,List<String> deletedImgUrls,Long postId, PostType postType) {
        //삭제되거나 추가되는 이미지가 없으면 return
        //TODO: newImages에 아무것도 보내지 않았을 때 빈 Multipartfile이 1개 포함되는 문제
        //  우선 newImages.get(0)이 비어있는 지로 수정되는 이미지가 있는지 판별
        //  원인 파악 필요
        if(newImages.get(0).isEmpty() && deletedImgUrls.size()==0) return;

        //삭제되는 이미지 삭제
        for(String deletedImgUrl : deletedImgUrls){
            s3Uploader.deleteImage(imageService.getFakeFileNameByImageUrl(deletedImgUrl));
            imageService.deleteImgByImgUrl(deletedImgUrl);
        }

        if(newImages.get(0).isEmpty()) return;

        //기존의 ImageList 에 추가되는 image 삽입
        List<Image> imageList = imageService.getImagesByPostTypeAndPostId(postType,postId);
        for(MultipartFile newImage : newImages){
            imageList.add(imageUtils.toImageEntity(postId, postType, newImage));
        }

        //ImageList 의 배열순서에 따라 우선순위 부여
        imageUtils.setPriority(imageList);
        imageService.savePostImage(imageList);
    }

    /* 게시글 삭제 이미지도 삭제 */
    public void removePostImage(Long postId, PostType postType) {
        List<String> fakeFiles = imageService.createKey(postId, postType);
        s3Uploader.deleteS3(fakeFiles, postId, postType);
        imageService.deleteImg(postId, postType);
    }
}
