package com.smile.petpat.post.category.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_TRADE_CATEGORY",
        indexes = {
                @Index(name = "trade_category_name_index",columnList = "TRADE_CATEGORY_NAME")

})
public class TradeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRADE_CATEGORY_ID")
    private Long tradeCategoryId;

    @Column(name = "TRADE_CATEGORY_NAME")
    private String tradeCategoryName;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_GROUP_ID",referencedColumnName = "CATEGORY_GROUP_ID",nullable = false)
    private CategoryGroup categoryGroup;

    public TradeCategory() {
    }

    public TradeCategory(Long tradeCategoryId, String tradeCategoryName, CategoryGroup categoryGroup) {
        this.tradeCategoryId = tradeCategoryId;
        this.tradeCategoryName = tradeCategoryName;
        this.categoryGroup = categoryGroup;
    }
}
