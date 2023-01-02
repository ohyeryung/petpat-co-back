package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_TRADE")
@Builder
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRADE_ID")
    private Long tradeId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "USER_ID",name = "USER_ID")
    private User user;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "POST_TYPE")
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @ManyToOne
    @JoinColumn(referencedColumnName = "",name = "TRADE_CATEGORY_DETAIL")
    private TradeCategoryDetail tradeCategoryDetail;

    public Trade() {
    }

    public Trade(Long tradeId, User user, String title, String content, Long price, String location, PostType postType, TradeCategoryDetail tradeCategoryDetail
    ) {
        this.tradeId = tradeId;
        this.user = user;
        this.title = title;
        this.content = content;
        this.price = price;
        this.location = location;
        this.postType = PostType.TRADE;
        this.tradeCategoryDetail = tradeCategoryDetail;
    }

    public Trade(Long tradeId, User user, String title, String content, Long price, String location, TradeCategoryDetail tradeCategoryDetail
    ) {
        this.tradeId = tradeId;
        this.user = user;
        this.title = title;
        this.content = content;
        this.price = price;
        this.location = location;
        this.postType = PostType.TRADE;
        this.tradeCategoryDetail = tradeCategoryDetail;
    }



}
