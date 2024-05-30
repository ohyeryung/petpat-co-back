package com.smile.petpat.image.domain;

import com.smile.petpat.image.repository.ImageRepository;
import com.smile.petpat.post.category.domain.PostType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ImageUploader {
    private final ImageRepository imageRepository;

    // 이미지 등록
    public List<Image> savePostImage(List<Image> imageList){
        return imageRepository.saveAll(imageList);
    }

    public Image toImageEntity(String originalFileName, String fakeFileName, String filePath, Long postId, PostType postType,ImagePriority imagePriority) {
        return new Image(originalFileName, fakeFileName, filePath, postId, postType,imagePriority);
    }

    public Image toImageEntity(String originalFileName, String fakeFileName, String filePath, Long postId, PostType postType ) {
        return new Image(originalFileName, fakeFileName, filePath, postId, postType);
    }

    // 로컬 이미지 삭제 (db)
    @Transactional
    public void deleteImg(Long postId, PostType postType) {
        imageRepository.deleteByPostIdAndPostType(postId, postType);
    }

    // 게시글 별 이미지 key 값 추출 메소드
    @Transactional
    public List<String> createKey(Long postId, PostType postType) {
        List<Image> images = imageRepository.findAllByPostIdAndPostTypeOrderByPostId(postId, postType);
        return images.stream().map(Image::getFakeFileName).collect(Collectors.toList());
    }


    // 게시글 이미지 url 리스트 추출 method
    @Transactional
    public List<String> readImgList(Long postId, PostType postType) {
        List<Image> images = imageRepository.findAllByPostIdAndPostTypeOrderByPostId(postId, postType);
        return images.stream().map(Image::getFilePath).collect(Collectors.toList());
    }

    public String repImg(Long postId, PostType postType) {
        Image image = imageRepository.findTop1ByPostIdAndPostTypeOrderByImageIdAsc(postId, postType);
        return image.getFilePath();
    }

    //이미지 url로 image의 FakeFileName 추출
    public String getFakeFileNameByImageUrl(String imageUrl){
        return imageRepository.findByFilePath(imageUrl).getFakeFileName();
    }

    //해당 postType 과 postId에 해당하는 이미지 모두 추출
    public List<Image> getImagesByPostTypeAndPostId(PostType postType,Long postId){
        return imageRepository.findAllByPostIdAndPostTypeOrderByPostId(postId,postType);
    }

    //로컬DB 이미지 삭제 by ImageUrl
    public void deleteImgByImgUrl(String filePath){
        imageRepository.deleteByFilePath(filePath);
    }

}
