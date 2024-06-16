package com.smile.petpat.post.trade.service;

import com.smile.petpat.image.dto.ImageResDto;
import com.smile.petpat.image.service.ImageService;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.post.common.Address.Dto.AddressReqDto;
import com.smile.petpat.post.common.Address.domain.Address;
import com.smile.petpat.post.common.Address.service.AddressService;
import com.smile.petpat.post.common.CommonUtils;
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
    private final ImageService imageService;
    private final CommonUtils commonUtils;
    private final AddressService addressService;

    @Override
    @Transactional
    public Long registerTrade(TradeCommand tradeCommand, User user) {
        //AddressChk
        Address address = addressService.getAddress(new AddressReqDto(tradeCommand));
        //1. 게시물 등록
        TradeCategoryDetail categoryDetail = tradeReader.readTradeCategoryDetailById(tradeCommand.getTradeCategoryDetailId());
        Trade initTrade = tradeCommand.toRegisterEntity(user,categoryDetail,address);

        Trade trade = tradeStore.store(initTrade);
        address.getTradelist().add(trade);
        //2. 사진 등록
        imageService.uploadPostImage(tradeCommand.getImages(),trade.getTradeId(),trade.getPostType());

        return trade.getTradeId();
    }

    // 게시물 수정
    @Override
    @Transactional
    public TradeInfo.TradeDetail updateTrade( User user,Long tradeId,TradeCommand tradeCommand) {
        //이미지를 제외한 게시글 수정
        Trade trade = tradeReader.userChk(tradeId, user.getId());
        TradeCategoryDetail categoryDetail = tradeReader.readTradeCategoryDetailById(tradeCommand.getTradeCategoryDetailId());
        Trade initTrade = tradeCommand.toUpdateEntity(user,tradeId,categoryDetail);
        trade.update(initTrade);

        //이미지 수정
        imageService.updateImage(tradeCommand.getImages(),tradeCommand.getDeletedImageId()
                            ,tradeId,PostType.TRADE);
        return getTradeInfo(tradeId, user, trade);
    }

    // 중고거래 게시판 목록 반환(로그인한 유저)
    @Override
    public TradeInfo.TradePagingListInfo listTrade(User user, Pageable pageable) {
        Page<TradeInfo.TradeList> listTrade = tradeReader.readTradeList(user,pageable);
        return new TradeInfo.TradePagingListInfo(listTrade);
    }

    @Override
    public TradeInfo.TradeDetail tradeDetail(Long tradeId) {
        List<ImageResDto> imgList = imageService.getImagesByPost(tradeId, PostType.TRADE);
        Trade trade = tradeReader.readTradeById(tradeId);
        trade.updateViewCnt(trade); //조회수 계산
        TradeInfo.TradeDetail tradeDetail =tradeReader.readTradeDetail(tradeId);


        return new TradeInfo.TradeDetail(tradeDetail,imgList);
    }

    @Override
    public TradeInfo.TradeDetail tradeDetailforUser(Long tradeId, User user) {
        Trade trade = tradeReader.readTradeById(tradeId);
        trade.updateViewCnt(trade);
        TradeInfo.TradeDetail tradeDetail = tradeReader.readTradeDetail(tradeId);
        List<ImageResDto> imageList = imageService.getImagesByPost(tradeId,trade.getPostType());
        // 조회수 계산
        return new TradeInfo.TradeDetail(tradeDetail,imageList);

    }


    @Override
    @Transactional
    public void deleteTrade(Long tradeId, User user) {
        // 1. 게시글 삭제
        tradeStore.delete(tradeId, user.getId());
        // 2. 해당 게시물 이미지 삭제
        imageService.removePostImage(tradeId, PostType.TRADE);
    }

    @Override
    public List<TradeInfo.TradeList> fetchTrendingTrade(User user) {
       return tradeReader.fetchTrendingTrade(user.getId());
    }

    private TradeInfo.TradeDetail getTradeInfo(Long tradeId, User user, Trade trade) {
        List<ImageResDto> imgList = imageService.getImagesByPost(tradeId, PostType.TRADE);
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
