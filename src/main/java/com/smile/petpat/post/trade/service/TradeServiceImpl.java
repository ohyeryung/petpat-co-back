package com.smile.petpat.post.trade.service;

import com.smile.petpat.image.domain.ImageUploadManager;
import com.smile.petpat.image.domain.ImageUploader;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.post.common.views.ViewsServiceImpl;
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
    private final ViewsServiceImpl viewsService;

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
        // 조회수 계산
        viewsService.updateViewCnt(tradeId, PostType.TRADE);
        List<String> imgList = imageUploader.createImgList(tradeId, PostType.TRADE);
        Trade trade = tradeReader.readTradeById(tradeId);
        return new TradeInfo(trade,imgList);
    }

    @Override
    @Transactional
    public TradeInfo updateTrade(TradeCommand tradeCommand, User user,Long tradeId) {
        TradeCategoryDetail categoryDetail = tradeReader.readTradeCategoryDetailById(tradeCommand.getTradeCategoryDetailId());
        Trade initTrade = tradeCommand.toUpdateEntity(user,tradeId,categoryDetail);
        Trade trade = tradeStore.update(initTrade,user.getId(),tradeId);
        List<String> imageList = imageUploadManager.updateImage(tradeCommand.getImages(), trade.getTradeId(), PostType.TRADE);
        TradeInfo tradeInfo = new TradeInfo(trade,imageList);
        return tradeInfo;
    }

    @Override
    @Transactional
    public void deleteTrade(Long tradeId, User user) {
        // 1. 게시글 삭제
        tradeStore.delete(tradeId, user.getId());
        // 2. 해당 게시물 이미지 삭제
        imageUploadManager.removePostImage(tradeId, PostType.TRADE);
    }



}
