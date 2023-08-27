package com.smile.petpat.post.qna.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.qna.domain.QnaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.smile.petpat.image.domain.QImage.image;
import static com.smile.petpat.post.qna.domain.QQna.qna;

public class QnaRepositoryImpl implements QnaRepositoryQueryDsl {
    private final JPAQueryFactory queryFactory;

    public QnaRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<QnaInfo.QnaList> qnaList(Long userId) {
        List<QnaInfo.QnaList> result;
               return result = queryFactory
                .select
                        (Projections.constructor
                                (QnaInfo.QnaList.class,
                                        qna.qnaId,
                                        qna.user.id,
                                        qna.user.nickname,
                                        qna.title,
                                        qna.postType,
                                        ExpressionUtils.as(
                                                JPAExpressions
                                                        .select(image.filePath)
                                                        .from(image)
                                                        .where(
                                                                image.postId.eq(qna.qnaId)
                                                                        .and(image.postType.eq(PostType.QNA))
                                                        )
                                                        .orderBy(image.imageId.asc()).limit(1)
                                                ,"image"),
                                        qna.viewCnt,
                                        qna.createdAt,
                                        qna.updatedAt

                                )
                        )
                .from(qna)
                .fetch();
    }

    @Override
    public Page<QnaInfo.QnaList> qnaListPaging(Long userId, Pageable pageable) {
        QueryResults<QnaInfo.QnaList> results;
        results = queryFactory
                .select
                        (Projections.constructor
                                (QnaInfo.QnaList.class,
                                        qna.qnaId,
                                        qna.user.id,
                                        qna.user.nickname,
                                        qna.title,
                                        qna.postType,
                                        ExpressionUtils.as(
                                                JPAExpressions
                                                        .select(image.filePath)
                                                        .from(image)
                                                        .where(
                                                                image.postId.eq(qna.qnaId)
                                                                        .and(image.postType.eq(PostType.QNA))
                                                        )
                                                        .orderBy(image.imageId.asc()).limit(1)
                                                ,"image"),
                                        qna.viewCnt,
                                        qna.createdAt,
                                        qna.updatedAt
                                )
                        )
                .from(qna)
                .orderBy(qna.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<QnaInfo.QnaList> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

}
