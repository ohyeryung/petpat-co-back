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

        public TradeDetail(Long tradeId, Long userId, String title, String content, Long price, String cityName, String cityCountryName, String townShipName, String detailAdName, String fullAdName, List<String> imageList, PostType postType, boolean isLiked, boolean isBookmarked, int viewCnt, Long likeCnt, Long bookmarkCnt, String tradeCategoryDetailName) {
            this.tradeId = tradeId;
            this.userId = userId;
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
        private Boolean booleanChk(Long chkValue) {
            return chkValue == 0?false : true;
        }
    }


}
