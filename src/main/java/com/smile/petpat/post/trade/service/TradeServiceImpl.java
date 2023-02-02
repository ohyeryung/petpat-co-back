package com.smile.petpat.post.trade.service;

import com.smile.petpat.image.domain.ImageUploadManager;
import com.smile.petpat.image.domain.ImageUploader;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.post.common.CommonUtils;
import com.smile.petpat.post.trade.domain.*;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    // 추후 querydsl로 변경예정
    @Override
    public List<TradeInfo> listTrade() {
        List<Trade> listTrade = tradeReader.readTradeList();
        List<TradeInfo> tradeInfos = listTrade.stream().map(TradeInfo::new).collect(Collectors.toList());
        return tradeInfos;
    }

    @Override
    public TradeInfo tradeDetail(Long tradeId) {
        List<String> imgList = imageUploader.createImgList(tradeId, PostType.TRADE);
        Trade trade = tradeReader.readTradeById(tradeId);
        // 조회수 계산
        trade.updateViewCnt(trade);
        return new TradeInfo(trade,imgList);
    }

    @Override
    public TradeInfo tradeDetailforUser(Long tradeId, User user) {
        Trade trade = tradeReader.readTradeById(tradeId);
        // 조회수 계산
        trade.updateViewCnt(trade);
        return getTradeInfo(tradeId, user, trade);

    }


    @Override
    @Transactional
    public TradeInfo updateTrade(TradeCommand tradeCommand, User user,Long tradeId) {
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

    private TradeInfo getTradeInfo(Long tradeId, User user, Trade trade) {
        List<String> imgList = imageUploader.createImgList(tradeId, PostType.TRADE);
        return new TradeInfo(trade, imgList,
                commonUtils.LikePostChk(tradeId, PostType.TRADE, user),
                commonUtils.BookmarkPostChk(tradeId, PostType.TRADE, user),
                commonUtils.getLikesCnt(tradeId, PostType.TRADE),
                commonUtils.getBookmarkCnt(tradeId, PostType.TRADE));
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
