package com.smile.petpat.post.rehoming.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smile.petpat.post.rehoming.domain.RehomingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.smile.petpat.post.common.bookmarks.domain.QBookmark.bookmark;
import static com.smile.petpat.post.common.likes.domain.QLikes.likes;
import static com.smile.petpat.post.rehoming.domain.QRehoming.rehoming;
import static com.smile.petpat.user.domain.QUser.user;

public class RehomingRepositoryImpl implements RehomingRepositoryQuerydsl {
    private final JPAQueryFactory queryFactory;

    public RehomingRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 분양 글 목록 조회 (회원)
    @Override
    public Page<RehomingInfo> rehomingListForMember(Long userId, Pageable pageable) {
        QueryResults<RehomingInfo> results = queryFactory
                .select(
                        Projections.constructor(
                                RehomingInfo.class,
                                rehoming.rehomingId,
                                rehoming.user.id,
                                rehoming.user.nickname,
                                rehoming.title,
                                rehoming.description,
                                rehoming.petName,
                                rehoming.petAge,
                                rehoming.category.categoryGroupName,
                                rehoming.type.petCategoryName,
                                rehoming.gender,
                                rehoming.cityName,
                                rehoming.cityCountryName,
                                rehoming.townShipName,
                                rehoming.detailAdName,
                                rehoming.fullAdName,
                                rehoming.price,
                                rehoming.status,
                                rehoming.postType,
                                rehoming.createdAt,
                                rehoming.updatedAt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(likes.count())
                                                .from(likes)
                                                .where(
                                                        likes.user.id.eq(userId)
                                                                .and(likes.postId.eq(rehoming.rehomingId))
                                                ), "isLiked"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(bookmark.count())
                                                .from(bookmark)
                                                .where(
                                                        bookmark.user.id.eq(userId)
                                                                .and(bookmark.postId.eq(rehoming.rehomingId))
                                                ), "isBookmarked"),
                                rehoming.viewCnt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(likes.count())
                                                .from(likes)
                                                .where(likes.postId.eq(rehoming.rehomingId)),
                                        "likeCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(bookmark.count())
                                                .from(bookmark)
                                                .where(bookmark.postId.eq(rehoming.rehomingId)),
                                        "bookmarkCnt")
                        )
                )
                .from(rehoming)
                .leftJoin(rehoming.user, user)
                .orderBy(rehoming.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<RehomingInfo> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    // 분양 글 목록 조회 (비회원)
    @Override
    public Page<RehomingInfo> rehomingList(Pageable pageable) {
        QueryResults<RehomingInfo> results;
        results = queryFactory
                .select(
                        Projections.constructor(
                                RehomingInfo.class,
                                rehoming.rehomingId,
                                rehoming.user.id,
                                rehoming.user.nickname,
                                rehoming.title,
                                rehoming.description,
                                rehoming.petName,
                                rehoming.petAge,
                                rehoming.category.categoryGroupName,
                                rehoming.type.petCategoryName,
                                rehoming.gender,
                                rehoming.cityName,
                                rehoming.cityCountryName,
                                rehoming.townShipName,
                                rehoming.detailAdName,
                                rehoming.fullAdName,
                                rehoming.price,
                                rehoming.status,
                                rehoming.postType,
                                rehoming.createdAt,
                                rehoming.updatedAt,
                                rehoming.viewCnt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(likes.count())
                                                .from(likes)
                                                .where(likes.postId.eq(rehoming.rehomingId)),
                                        "likeCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(bookmark.count())
                                                .from(bookmark)
                                                .where(bookmark.postId.eq(rehoming.rehomingId)),
                                        "bookmarkCnt")
                        )
                )
                .from(rehoming)
                .leftJoin(rehoming.user, user)
                .orderBy(rehoming.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<RehomingInfo> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

}
