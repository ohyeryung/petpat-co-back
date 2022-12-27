package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.image.domain.ImageUploadManager;
import com.smile.petpat.image.domain.S3Uploader;
import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.post.rehoming.dto.RehomingReqDto;
import com.smile.petpat.post.rehoming.dto.RehomingResDto;
import com.smile.petpat.post.rehoming.repository.RehomingRepository;
import com.smile.petpat.tag.service.TagService;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RehomingService {
    private final RehomingRepository rehomingRepository;
    private final ImageUploadManager imageUploadManager;
    private final TagService tagService;

    // 1. 분양 글 등록
    public RehomingResDto createRehoming(User user, List<MultipartFile> rehomingImg, RehomingReqDto requestDto) {

        List<String> filePath = saveImg(rehomingImg);

//        RehomingReqDto rehomingDto = new RehomingReqDto(requestDto.getTitle(),requestDto.getDescription(),
//                requestDto.getPetName(), requestDto.getPetAge(), requestDto.getCategory(),requestDto.getType(),
//                requestDto.getGender(),requestDto.getRegion(), requestDto.getPrice(), filePath, requestDto.getTagList());

        RehomingReqDto rehomingDto = requestDto.toRehomingDto(filePath);
        Rehoming rehoming = rehomingRepository.save(new Rehoming(user, rehomingDto));

        tagService.saveTag(requestDto.getTagList());

        return new RehomingResDto(rehoming, filePath, requestDto.getTagList());
    }

    // 1-1.분양 이미지 업로드
    public List<String> saveImg(List<MultipartFile> rehomingImg) {
        return imageUploadManager.uploadPostImage(rehomingImg);
    }

    // 분양 글 목록 조회
    public RehomingPagingDto readRehoming(Pageable pageable) {
        List<Rehoming> rehoming = rehomingRepository.findAll();
        System.out.println("rehoming.size() = " + rehoming.size());
        return null;
    }

    // 분양 글 수정
    public Long putRehoming(Long postId, RehomingReqDto rehomingDto) {
//        Rehoming rehoming = rehomingRepository.findByIdAndUserId(postId, userId) {
//
//        }
        return null;
    }
}