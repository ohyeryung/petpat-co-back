package com.smile.petpat.image.service;

import com.smile.petpat.image.domain.Image;
import com.smile.petpat.image.dto.ImageResDto;
import com.smile.petpat.image.repository.ImageRepository;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.rehoming.dto.RehomingResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    // 이미지 등록
    public List<Image> savePostImage(List<Image> imageList){
        return imageRepository.saveAll(imageList);
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

    @Transactional
    public List<ImageResDto> readImgListNew(Long postId, PostType postType) {
        List<Image> images = imageRepository.findAllByPostIdAndPostTypeOrderByPostId(postId, postType);
        List<ImageResDto> imageResDtoList= new ArrayList<>();
        for(Image image : images){
            ImageResDto imageResDto =new ImageResDto(image.getImageId(),
                    image.getFilePath());
            imageResDtoList.add(imageResDto);
        }
        return imageResDtoList;
    }

    //이미지 url로 image의 FakeFileName 추출
    public String getFakeFileNameByImageUrl(String imageUrl){
        return imageRepository.findByFilePath(imageUrl).getFakeFileName();
    }

    //이미지 Id로 image의 FakeFileName 추출
    public String getFakeFileNameByImageId(Long imageId){
        return imageRepository.findById(imageId).get().getFakeFileName();
    }

    //해당 postType 과 postId에 해당하는 이미지 모두 추출
    public List<Image> getImagesByPostTypeAndPostId(PostType postType,Long postId){
        return imageRepository.findAllByPostIdAndPostTypeOrderByPostId(postId,postType);
    }

    //로컬DB 이미지 삭제 by ImageUrl
    public void deleteImgByImgUrl(String filePath){
        imageRepository.deleteByFilePath(filePath);
    }

    //로컬DB 이미지 삭제 by Id
    public void deleteImgByImageId(Long imageId){
        imageRepository.deleteById(imageId);
    }

}
