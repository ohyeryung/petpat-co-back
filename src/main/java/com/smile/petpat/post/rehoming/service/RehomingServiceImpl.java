package com.smile.petpat.post.rehoming.service;

import com.smile.petpat.image.domain.ImageUploadManager;
import com.smile.petpat.image.domain.ImageUploader;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.CommonUtils;
import com.smile.petpat.post.common.bookmarks.repository.BookmarkRepository;
import com.smile.petpat.post.common.likes.repository.LikesRepository;
import com.smile.petpat.post.common.views.ViewsServiceImpl;
import com.smile.petpat.post.rehoming.domain.*;
import com.smile.petpat.post.rehoming.dto.RehomingResDto;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RehomingServiceImpl implements RehomingService{
    private final ImageUploadManager imageUploadManager;
    private final RehomingStore rehomingStore;
    private final RehomingReader rehomingReader;
    private final ImageUploader imageUploader;
    private final ViewsServiceImpl viewsService;
    private final CommonUtils commonUtils;
    private final LikesRepository likesRepository;
    private final BookmarkRepository bookmarkRepository;

    // 1. 분양 글 등록
    @Override
    @Transactional
    public void registerRehoming(User user, List<MultipartFile> rehomingImg, RehomingCommand rehomingCommand) {

        // 1-1. 게시물 등록
        Rehoming initRehoming = rehomingCommand.toRegisterEntity(user);
        Rehoming rehoming = rehomingStore.store(initRehoming);

        // 1-2. 이미지 등록
        Long postId = rehoming.getRehomingId();
        imageUploadManager.uploadPostImage(rehomingImg, postId, PostType.REHOMING);

    }

    // 2. 분양 글 목록 조회
    @Override
    public List<RehomingInfo> listRehoming() {
        List<Rehoming> listRehoming = rehomingReader.readRehomingList();
        return listRehoming.stream()
                .map(RehomingInfo::new).collect(Collectors.toList());
    }

    // 3-1. 분양 글 상세 조회 (비회원)
    @Override
    @Transactional
    public RehomingResDto detailRehoming(Long postId) {
        // 조회수 계산
        viewsService.updateViewCnt(postId, PostType.REHOMING);
        Rehoming rehoming = rehomingReader.readRehomingById(postId);
        List<String> imgList = imageUploader.createImgList(postId, PostType.REHOMING);
        return new RehomingResDto(rehoming, imgList);
    }

    // 3-2. 분양 글 상세 조회 (회원)
    @Override
    @Transactional
    public RehomingResDto detailRehomingForMember(Long postId, User user) {
        // 조회수 계산
        viewsService.updateViewCnt(postId, PostType.REHOMING);
        Rehoming rehoming = rehomingReader.readRehomingById(postId);
        return getResDto(user, postId, rehoming);
    }

    // 4. 분양 글 수정
    /*
     * TODO : 1. 수정 시 게시글 생성시간 null 로 변경되는 현상 (논의 필요)
     *        2. 수정 후 게시글 조회수 초기화 되는 현상 (논의 필요)
     *  */
    @Override
    @Transactional
    public RehomingResDto updateRehoming(User user, Long postId, RehomingCommand rehomingCommand, List<MultipartFile> rehomingImg) {

        log.info("0. 저장 전 조회수 = " + rehomingCommand.getViewCnt());
        // 4-1. 게시글 수정 후 저장
        Rehoming initRehoming = rehomingCommand.toUpdateEntity(user, postId);
        log.info("1. 저장 전 조회수 = " + initRehoming.getViewCnt());
        Rehoming rehoming = rehomingStore.update(initRehoming, user.getId(), postId);
        log.info("2. 저장 후 조회수 = " + rehoming.getViewCnt());

        // 4-2. 이미지 수정 후 저장
        imageUploadManager.updateImage(rehomingImg, postId, PostType.REHOMING);
        return getResDto(user, postId, rehoming);
    }

    // 5. 분양 글 삭제
    @Override
    @Transactional
    public void deleteRehoming(User user, Long rehomingId) {
        // 5-1. 게시글 삭제
        rehomingStore.delete(user.getId(), rehomingId);
        // 5-2. 해당 게시글 이미지 삭제
        imageUploadManager.removePostImage(rehomingId, PostType.REHOMING);
    }

    private RehomingResDto getResDto(User user, Long postId, Rehoming rehoming) {
        List<String> imgList = imageUploader.createImgList(postId, PostType.REHOMING);
        int bookmarkCnt = bookmarkRepository.findByPostIdAndPostType(postId, PostType.REHOMING).size();
        int likeCnt = likesRepository.findByPostIdAndPostType(postId, PostType.REHOMING).size();
        return new RehomingResDto(rehoming, imgList,
                commonUtils.LikePostChk(postId, PostType.REHOMING, user),
                commonUtils.BookmarkPostChk(postId, PostType.REHOMING, user), likeCnt, bookmarkCnt);
    }
}