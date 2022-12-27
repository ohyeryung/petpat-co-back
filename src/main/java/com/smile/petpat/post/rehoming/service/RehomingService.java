package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.image.domain.ImageUploadManager;
import com.smile.petpat.image.domain.S3Uploader;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.rehoming.domain.*;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.post.rehoming.dto.RehomingResDto;
import com.smile.petpat.post.rehoming.repository.RehomingRepository;
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
    private final ImageUploadManager imageUploadManager;
    private final TagService tagService;
    private final RehomingStore rehomingStore;
    private final S3Uploader s3Uploader;
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

        tagService.saveTag(requestDto.getTagList());

        return new RehomingResDto(rehoming, filePath, requestDto.getTagList());
    }

    // 2. 분양 글 목록 조회
    @Transactional
    public RehomingPagingDto readRehoming(Pageable pageable) {

        List<Rehoming> rehoming = rehomingRepository.findAll();
        return new RehomingPagingDto(rehoming);
    }

    // 2-1. 분양 글 목록 조회
    public List<RehomingInfo> listRehoming() {
        List<Rehoming> listRehoming = rehomingReader.readRehomingList();
        List<RehomingInfo> rehomingInfos = listRehoming.stream()
                .map(RehomingInfo::new).collect(Collectors.toList());
        return rehomingInfos;
    }

    // 3. 분양 글 상세 조회
    @Transactional
    public RehomingResDto detailRehoming(Long postId) {
        Rehoming rehoming = rehomingReader.readRehomingById(postId);
        List<String> imgList = s3Uploader.createImgList(postId, PostType.REHOMING);
        // 조회수 카운트 하는 method 들어갈 자리
        /*
        * TODO : count method 추가 + boolean 값 처리 방안 고안 */
        return new RehomingResDto(rehoming, imgList);
    }

    // 4. 분양 글 수정
    /*
    * TODO : 수정 시 게시글 생성시간 null 로 변경되는 현상 (수정 필요)
    *  */
    @Transactional
    public RehomingResDto putRehoming(User user, Long postId, RehomingCommand rehomingCommand, List<MultipartFile> rehomingImg) {

        // 4-1. 게시글 수정 후 저장
        Rehoming initRehoming = rehomingCommand.toUpdateEntity(user, postId);
        Rehoming rehoming = rehomingStore.update(initRehoming, user.getId(), postId);

        // 4-2. 이미지 수정 후 저장
        s3Uploader.updateFile(rehomingImg, postId, PostType.REHOMING);
        List<String> imgList = s3Uploader.createImgList(postId, PostType.REHOMING);
        RehomingResDto rehomingInfo = new RehomingResDto(rehoming, imgList);
        return rehomingInfo;
    }

    // 5. 분양 글 삭제
    @Transactional
    public void deleteRehoming(User user, Long rehomingId) {
        // 5-1. 게시글 삭제
        rehomingStore.delete(user.getId(), rehomingId);
        // 5-2. 해당 게시글 이미지 삭제
        s3Uploader.deleteS3(rehomingId, PostType.REHOMING);
        s3Uploader.deleteImg(rehomingId, PostType.REHOMING);
    }
}