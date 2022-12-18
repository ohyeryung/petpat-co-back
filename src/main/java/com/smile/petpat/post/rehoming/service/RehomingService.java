package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.image.domain.S3Uploader;
import com.smile.petpat.image.repository.ImageRepository;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.rehoming.domain.*;
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RehomingService {
    private final RehomingRepository rehomingRepository;
    private final TagService tagService;
    private final RehomingStore rehomingStore;
    private final S3Uploader s3Uploader;
    private final ImageRepository imageRepository;
    private final RehomingReader rehomingReader;

    // 1. 분양 글 등록
    @Transactional
    public void createRehoming(User user, List<MultipartFile> rehomingImg, RehomingCommand rehomingCommand) {

        // 1-1. 게시물 등록
        Rehoming initRehoming = rehomingCommand.toRegisterEntity(user);
        Rehoming rehoming = rehomingStore.store(initRehoming);

        // 1-2. 이미지 등록
        Long postId = rehoming.getRehomingId();
        s3Uploader.uploadFile(rehomingImg, postId, PostType.REHOMING);

    }

    // 2. 분양 글 목록 조회
    public RehomingPagingDto readRehoming(Pageable pageable) {

        List<Rehoming> rehoming = rehomingRepository.findAll();

        return new RehomingPagingDto(rehoming);
    }

    // 3. 분양 글 상세 조회
    public RehomingResDto detailRehoming(Long postId) {
        Rehoming rehoming = rehomingRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다")
        );
        List<String> imgList = s3Uploader.createImgList(postId, PostType.REHOMING);
        // 조회수 카운트 하는 method 들어갈 자리
        /*
        * TODO : count method 추가 + boolean 값 처리 방안 고안 */
        return new RehomingResDto(rehoming, imgList ,false, false, 0,0);
    }

    // 분양 글 수정
    public Long putRehoming(User user, Long postId, RehomingReqDto rehomingDto) {
//        Rehoming rehoming = rehomingRepository.findByIdAndUserId(postId, userId) {
//
//        }
        return null;
    }

    // 2-1. 분양 글 목록 조회
    public List<RehomingInfo> listRehoming() {
        List<Rehoming> listRehoming = rehomingReader.readRehomingList();
        List<RehomingInfo> rehomingInfos = listRehoming.stream()
                .map(RehomingInfo::new).collect(Collectors.toList());
        return rehomingInfos;
    }
}