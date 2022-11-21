package com.smile.petpat.rehoming.service;

import com.smile.petpat.Image.S3Uploader;
import com.smile.petpat.rehoming.domain.Rehoming;
import com.smile.petpat.rehoming.dto.RehomingDto;
import com.smile.petpat.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.rehoming.repository.RehomingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service

public class RehomingService {
    private final RehomingRepository rehomingRepository;
    private final S3Uploader s3Uploader;

    public Rehoming createRehoming(List<MultipartFile> rehomingImg, RehomingDto requestDto) {

        List<String> filePath = s3Uploader.uploadFile(rehomingImg);

        RehomingDto rehomingDto = new RehomingDto(requestDto.getTitle(),requestDto.getDescription(),
                requestDto.getPetName(), requestDto.getPetAge(), requestDto.getCategory(),requestDto.getType(),
                requestDto.getGender(),requestDto.getRegion(), requestDto.getPrice(), requestDto.isCompleted(), filePath);

        return rehomingRepository.save(new Rehoming(rehomingDto));
    }

    public RehomingPagingDto readRehoming(Pageable pageable) {
        List<Rehoming> rehoming = rehomingRepository.findAll();
        System.out.println("rehoming.size() = " + rehoming.size());
        return null;
    }

    public Object putRehoming(int postId) {
        return null;
    }
}