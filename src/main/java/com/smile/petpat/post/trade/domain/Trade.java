package com.smile.petpat.post.trade.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
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
    private String tradeId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PRICE")
    private String price;

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


    public Trade(String tradeId, String title, String content, String price, String location, PostType postType) {
      Trade.builder()
              .tradeId(tradeId)
              .title(title)
              .content(content)
              .price(price)
              .location(location)
              .postType(postType)
              .build();
    }
}
