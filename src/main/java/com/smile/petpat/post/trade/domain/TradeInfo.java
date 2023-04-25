package com.smile.petpat.post.trade.domain;

import com.smile.petpat.image.domain.Image;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.CalculateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TradeInfo {


    @Builder
    @Getter
    @ToString
    public static class TradeDetail{
        private Long tradeId;
        private Long userId;
        private String nickname;
        private String title;
        private String content;
        private Long price;
        private String cityName;
        private String cityCountryName;
        private String townShipName;
        private String detailAdName;
        private String fullAdName;
        private List<String> imageList;
        private PostType postType;
        private boolean isLiked;
        private boolean isBookmarked;
        private int viewCnt;
        private Long likeCnt;
        private Long bookmarkCnt;
        private String tradeCategoryDetailName;

        private LocalDateTime createAt;

        public TradeDetail(){
            
        }

        public TradeDetail(Long tradeId, Long userId, String nickname, String title, String content, Long price,
                           String cityName, String cityCountryName, String townShipName, String detailAdName,
                           String fullAdName, List<String> imageList, PostType postType, boolean isLiked,
                           boolean isBookmarked, int viewCnt, Long likeCnt, Long bookmarkCnt, String tradeCategoryDetailName, LocalDateTime createAt
        ) {
            this.tradeId = tradeId;
            this.userId = userId;
            this.nickname = nickname;
            this.title = title;
            this.content = content;
            this.price = price;
            this.cityName = cityName;
            this.cityCountryName = cityCountryName;
            this.townShipName = townShipName;
            this.detailAdName = detailAdName;
            this.fullAdName = fullAdName;
            this.imageList = imageList;
            this.postType = postType;
            this.isLiked = isLiked;
            this.isBookmarked = isBookmarked;
            this.viewCnt = viewCnt;
            this.likeCnt = likeCnt;
            this.bookmarkCnt = bookmarkCnt;
            this.tradeCategoryDetailName = tradeCategoryDetailName;
            this.createAt = createAt;
        }

        public TradeDetail(Long tradeId, Long userId, String nickname, String title, String content, Long price, String cityName, String cityCountryName, String townShipName, String detailAdName, String fullAdName, PostType postType, Long isLiked, Long isBookmarked, int viewCnt, Long likeCnt, Long bookmarkCnt, String tradeCategoryDetailName) {
            this.tradeId = tradeId;
            this.userId = userId;
            this.nickname = nickname;
            this.title = title;
            this.content = content;
            this.price = price;
            this.cityName = cityName;
            this.cityCountryName = cityCountryName;
            this.townShipName = townShipName;
            this.detailAdName = detailAdName;
            this.fullAdName = fullAdName;
            this.postType = postType;
            this.isLiked = booleanChk(isLiked);
            this.isBookmarked = booleanChk(isBookmarked);
            this.viewCnt = viewCnt;
            this.likeCnt = likeCnt;
            this.bookmarkCnt = bookmarkCnt;
            this.tradeCategoryDetailName = tradeCategoryDetailName;
        }

        public TradeDetail(TradeDetail tradeDetail, List<String> imageList) {
            this.tradeId = tradeDetail.tradeId;
            this.userId = tradeDetail.userId;
            this.nickname = tradeDetail.nickname;
            this.title = tradeDetail.title;
            this.content = tradeDetail.content;
            this.price = tradeDetail.price;
            this.cityName = tradeDetail.cityName;
            this.cityCountryName = tradeDetail.cityCountryName;
            this.townShipName = tradeDetail.townShipName;
            this.detailAdName = tradeDetail.detailAdName;
            this.fullAdName = tradeDetail.fullAdName;
            this.imageList = imageList;
            this.postType = tradeDetail.postType;
            this.isLiked = tradeDetail.isLiked;
            this.isBookmarked = tradeDetail.isBookmarked;
            this.viewCnt = tradeDetail.viewCnt;
            this.likeCnt = tradeDetail.likeCnt;
            this.bookmarkCnt = tradeDetail.bookmarkCnt;
            this.tradeCategoryDetailName = tradeDetail.tradeCategoryDetailName;
        }




    }
    @Getter
    @ToString
    public static class TradeList{
        private Long tradeId;
        private String nickname;
        private String title;
        private Long price;
        private String cityName;
        private String cityCountryName;
        private String imagePath;
        private boolean isLiked;
        private boolean isBookmarked;
        private int viewCnt;
        private Long likeCnt;
        private Long bookmarkCnt;

        private String createAt;

        public TradeList(){
           
        }


        public TradeList(Long tradeId, String nickname, String title, Long price, String cityName, String cityCountryName, String imagePath, Long isLiked, Long isBookmarked, int viewCnt, Long likeCnt, Long bookmarkCnt, LocalDateTime createAt) {
            this.tradeId = tradeId;
            this.nickname = nickname;
            this.title = title;
            this.price = price;
            this.cityName = cityName;
            this.cityCountryName = cityCountryName;
            this.imagePath = imagePath;
            this.isLiked = booleanChk(isLiked);
            this.isBookmarked = booleanChk(isBookmarked);
            this.viewCnt = viewCnt;
            this.likeCnt = likeCnt;
            this.bookmarkCnt = bookmarkCnt;
            this.createAt = CalculateTime.dateformatForPost(createAt);
        }

    }

    public static  Boolean booleanChk(Long chkValue) {
        return chkValue == 0?false : true;
    }
}
