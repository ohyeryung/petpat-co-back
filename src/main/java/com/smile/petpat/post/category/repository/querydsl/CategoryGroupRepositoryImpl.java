package com.smile.petpat.post.category.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smile.petpat.post.category.dto.PostCategoryInfo;
import javax.persistence.EntityManager;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.smile.petpat.post.category.domain.QTradeCategoryDetail.tradeCategoryDetail;
import static com.smile.petpat.post.trade.domain.QTrade.trade;

public class CategoryGroupRepositoryImpl implements CategoryRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    public CategoryGroupRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<PostCategoryInfo.TradeCategoryDetailResponse> getTradeCategoryAndCnt(Long tradeCategoryId) {

//        List<PostCategoryInfo.TradeCategoryResponse> result2 = queryFactory
//                .select(
//                        Projections.constructor(
//                                PostCategoryInfo.TradeCategoryResponse.class,
//                                tradeCategory.tradeCategoryId,
//                                tradeCategory.tradeCategoryName,
//                                qu
//                        )
//                )

        QueryResults<PostCategoryInfo.TradeCategoryDetailResponse> results = queryFactory
                .select(
                        Projections.constructor(
                                PostCategoryInfo.TradeCategoryDetailResponse.class,
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
}
