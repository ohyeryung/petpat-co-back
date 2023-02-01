package com.smile.petpat.post.category.domain;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_TRADE_CATEGORY_DETAIL",
        indexes = {@Index(name = "trade_category_detail_index", columnList = "TRADE_CATEGORY_DETAIL_NAME")

})
public class TradeCategoryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRADE_CATEGORY_DETAIL_ID")
    private Long tradeCategoryDetailId;

    @Column(name = "TRADE_CATEGORY_DETAIL_NAME")
    private String tradeCategoryDetailName;

    @ManyToOne
    @JoinColumn(referencedColumnName = "TRADE_CATEGORY_ID",name = "TRADE_CATEGORY_ID")
    private TradeCategory tradeCategory;
}
