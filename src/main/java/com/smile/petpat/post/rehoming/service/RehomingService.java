package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.image.domain.S3Uploader;
import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.dto.RehomingDto;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.post.rehoming.dto.RehomingResponseDto;
import com.smile.petpat.post.rehoming.repository.RehomingRepository;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RehomingService {
    private final RehomingRepository rehomingRepository;
    private final S3Uploader s3Uploader;

    // 1. 분양 글 등록
    public RehomingResponseDto createRehoming(User user, List<MultipartFile> rehomingImg, RehomingDto requestDto) {

        List<String> filePath = s3Uploader.uploadFile(rehomingImg);

        RehomingDto rehomingDto = new RehomingDto(requestDto.getTitle(),requestDto.getDescription(),
                requestDto.getPetName(), requestDto.getPetAge(), requestDto.getCategory(),requestDto.getType(),
                requestDto.getGender(),requestDto.getRegion(), requestDto.getPrice(), filePath);

        Rehoming rehoming = rehomingRepository.save(new Rehoming(user, rehomingDto));

        return new RehomingResponseDto(rehoming);
    }

    // 분양 글 목록 조회
    public RehomingPagingDto readRehoming(Pageable pageable) {
        List<Rehoming> rehoming = rehomingRepository.findAll();
        System.out.println("rehoming.size() = " + rehoming.size());
        return null;
    }

    // 분양 글 수정
    public Long putRehoming(Long postId, RehomingDto rehomingDto) {
//        Rehoming rehoming = rehomingRepository.findByIdAndUserId(postId, userId) {
//
//        }
        return null;
    }
}