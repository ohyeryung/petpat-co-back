package com.smile.petpat.post.category.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smile.petpat.post.category.dto.PostCategoryDto;
import com.smile.petpat.post.category.dto.PostCategoryInfo;
import javax.persistence.EntityManager;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.smile.petpat.post.category.domain.QTradeCategoryDetail.tradeCategoryDetail;
import static com.smile.petpat.post.trade.domain.QTrade.trade;
import static com.smile.petpat.post.rehoming.domain.QRehoming.rehoming;
import static com.smile.petpat.post.category.domain.QPetCategory.petCategory;


public class CategoryGroupRepositoryImpl implements CategoryRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    public CategoryGroupRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<PostCategoryDto.TradeCategoryDetailResponse> getTradeCategoryAndCnt(Long tradeCategoryId) {
        QueryResults<PostCategoryDto.TradeCategoryDetailResponse> results = queryFactory
                .select(
                        Projections.constructor(
                                PostCategoryDto.TradeCategoryDetailResponse.class,
                                tradeCategoryDetail.tradeCategoryDetailId,
                                tradeCategoryDetail.tradeCategoryDetailName,
                                Expressions.as(
                                        select(trade.count())
                                                .from(trade)
                                                .where(trade.tradeCategoryDetail.tradeCategoryDetailId.eq(tradeCategoryDetail.tradeCategoryDetailId))
                                                ,"tradeCategoryDetailCnt"
                                )
                        )
                )
                .from(tradeCategoryDetail)
                .where(tradeCategoryDetail.tradeCategory.tradeCategoryId.eq(tradeCategoryId))
                .fetchResults();
        return results.getResults();
    }

    @Override
    public List<PostCategoryDto.RehomingCategoryResponse> getRehomingCategoryAndCnt(Long categoryGroupId) {
        QueryResults<PostCategoryDto.RehomingCategoryResponse> results= queryFactory
                .select(
                        Projections.constructor(
                                PostCategoryDto.RehomingCategoryResponse.class,
                                petCategory.petCategoryId,
                                petCategory.petCategoryName,
                                Expressions.as(
                                        select(rehoming.count())
                                                .from(rehoming)
                                                .where(rehoming.type.petCategoryId.eq(petCategory.petCategoryId))
                                        ,"petCategoryCnt"
                                )
                        )
                )
                .from(petCategory)
                .where(petCategory.categoryGroup.categoryGroupId.eq(categoryGroupId))
                .fetchResults();
        return results.getResults();
    }
}
