package com.smile.petpat.post.trade.service;

import com.smile.petpat.image.domain.ImageUploadManager;
import com.smile.petpat.image.domain.ImageUploader;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.post.common.CommonUtils;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.post.trade.domain.*;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService{

    private final TradeStore  tradeStore;
    private final TradeReader tradeReader;
    private final ImageUploadManager imageUploadManager;
    private final ImageUploader imageUploader;
    private final CommonUtils commonUtils;

    @Override
    @Transactional
    public void registerTrade(TradeCommand tradeCommand, User user) {
        //1. 게시물 등록
        TradeCategoryDetail categoryDetail = tradeReader.readTradeCategoryDetailById(tradeCommand.getTradeCategoryDetailId());
        Trade initTrade = tradeCommand.toRegisterEntity(user,categoryDetail);
        Trade trade = tradeStore.store(initTrade);
        //2. 사진 등록
        imageUploadManager.uploadPostImage(tradeCommand.getImages(),trade.getTradeId(),trade.getPostType());
    }

    // 중고거래 게시판 목록 반환(로그인한 유저)
    @Override
    public RehomingPagingDto listTrade(User user, Pageable pageable) {
        Page<TradeInfo.TradeList> listTrade = tradeReader.readTradeList(user,pageable);
        RehomingPagingDto dto= new RehomingPagingDto(listTrade);
        return dto;
    }

    @Override
    public TradeInfo.TradeDetail tradeDetail(Long tradeId) {
        List<String> imgList = imageUploader.readImgList(tradeId, PostType.TRADE);
        Trade trade = tradeReader.readTradeById(tradeId);

        // 조회수 계산
        trade.updateViewCnt(trade);
        return new TradeInfo.TradeDetail();
    }

    @Override
    public TradeInfo.TradeDetail tradeDetailforUser(Long tradeId, User user) {
        Trade trade = tradeReader.readTradeById(tradeId);
        trade.updateViewCnt(trade);
        TradeInfo.TradeDetail tradeDetail = tradeReader.readTradeDetail(user.getId(), tradeId);
        List<String> imageList = imageUploader.readImgList(tradeId,trade.getPostType());
        // 조회수 계산
        return new TradeInfo.TradeDetail(tradeDetail,imageList);

    }


    @Override
    @Transactional
    public TradeInfo.TradeDetail updateTrade(TradeCommand tradeCommand, User user, Long tradeId) {
        TradeCategoryDetail categoryDetail = tradeReader.readTradeCategoryDetailById(tradeCommand.getTradeCategoryDetailId());
        Trade initTrade = tradeCommand.toUpdateEntity(user,tradeId,categoryDetail);
        Trade trade = tradeStore.update(initTrade,user.getId(),tradeId);
        return getTradeInfo(tradeId, user, trade);
    }

    @Override
    @Transactional
    public void deleteTrade(Long tradeId, User user) {
        // 1. 게시글 삭제
        tradeStore.delete(tradeId, user.getId());
        // 2. 해당 게시물 이미지 삭제
        imageUploadManager.removePostImage(tradeId, PostType.TRADE);
    }

    private TradeInfo.TradeDetail getTradeInfo(Long tradeId, User user, Trade trade) {
        List<String> imgList = imageUploader.readImgList(tradeId, PostType.TRADE);
        return new TradeInfo.TradeDetail();
    }


    @Override
    public void updateStatusFinding(User user, Long postId) {
        Trade trade = tradeReader.readTradeById(postId);
        trade.isFinding();
    }

    @Override
    public void updateStatusReserved(User user, Long postId) {
        Trade trade = tradeReader.readTradeById(postId);
        trade.isReserved();
    }

    @Override
    public void updateStatusMatched(User user, Long postId) {
        Trade trade = tradeReader.readTradeById(postId);
        trade.isMatched();
    }


}
