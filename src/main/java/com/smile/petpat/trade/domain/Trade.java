package com.smile.petpat.trade.domain;

import com.smile.petpat.category.domain.PostType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "trade")
@Builder
public class Trade {

    @Id
    private String tradeId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String price;

    @Column
    private String location;

    @Column
    @Enumerated(EnumType.STRING)
    private PostType postType;

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
