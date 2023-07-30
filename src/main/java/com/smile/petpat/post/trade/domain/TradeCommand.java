package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.post.common.status.PostStatus;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@ToString
public class TradeCommand {
    private User user;
    private String title;
    private String content;
    private Long price;
    private String cityName;
    private String cityCountryName;
    private String townShipName;
    private String detailAdName;
    private String fullAdName;
    private PostType postType;
    private PostStatus postStatus;
    private Long tradeCategoryDetailId;
    private List<MultipartFile> images;

    public TradeCommand(){

    }
    @Builder
    public TradeCommand(User user, String title, String content, Long price, String cityName, String cityCountryName, String townShipName, String detailAdName, String fullAdName, Long tradeCategoryDetailId, List<MultipartFile> images) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.price = price;
        this.cityName = cityName;
        this.cityCountryName = cityCountryName;
        this.townShipName = townShipName;
        this.detailAdName = detailAdName;
        this.fullAdName = fullAdName;
        this.tradeCategoryDetailId = tradeCategoryDetailId;
        this.images = images;
    }

    public Trade toRegisterEntity(User user, TradeCategoryDetail tradeCategoryDetail){
        return Trade.builder()
                .user(user)
                .title(title)
                .content(content)
                .price(price)
                .cityName(cityName)
                .cityCountryName(cityCountryName)
                .townShipName(townShipName)
                .detailAdName(detailAdName)
                .fullAdName(fullAdName)
                .tradeCategoryDetail(tradeCategoryDetail)
                .build();
    }

    public Trade toUpdateEntity(User user,Long tradeId,TradeCategoryDetail tradeCategoryDetail){
        return Trade.builder()
                .tradeId(tradeId)
                .user(user)
                .title(title)
                .content(content)
                .price(price)
                .cityName(cityName)
                .cityCountryName(cityCountryName)
                .townShipName(townShipName)
                .detailAdName(detailAdName)
                .fullAdName(fullAdName)
                .postType(PostType.TRADE)
                .status(PostStatus.TRADE_FINDING)
                .tradeCategoryDetail(tradeCategoryDetail)
                .build();
    }
}
