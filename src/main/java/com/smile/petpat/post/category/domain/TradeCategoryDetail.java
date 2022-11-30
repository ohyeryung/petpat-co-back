package com.smile.petpat.post.category.domain;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_TRADE_CATEGORY_DETAIL")
public class TradeCategoryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRADE_CATEGORY_DETAIL_ID")
    private Long tradeCategoryDetailId;

    @Column(name = "TRADE_CATEGORY_NAME")
    private String tradeCategoryName;

    @ManyToOne
    @JoinColumn(referencedColumnName = "")
    private TradeCategory tradeCategory;
}
