package com.smile.petpat.user.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.user.dto.ProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.smile.petpat.image.domain.QImage.image;
import static com.smile.petpat.post.rehoming.domain.QRehoming.rehoming;
import static com.smile.petpat.user.domain.QUser.user;
import static com.smile.petpat.post.qna.domain.QQna.qna;
import static com.smile.petpat.post.qna.domain.QComment.comment;

public class UserRepositoryImpl implements ProfileRepositoryQuerydsl {
    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ProfileDto.RehomingResponse> getMyRehoming(Long userId, Pageable pageable) {
        QueryResults<ProfileDto.RehomingResponse> results = queryFactory
                .select(
                        Projections.constructor(
                                ProfileDto.RehomingResponse.class,
                                rehoming.rehomingId,
                                Expressions.as(
                                        select(image.filePath)
                                                .from(image)
                                                .where(image.postId.eq(rehoming.rehomingId),
                                                        image.postType.eq(PostType.REHOMING),
                                                        image.repImgNY.eq(true))
                                        , "rehomingImg"),
                                rehoming.title,
                                rehoming.createdAt,
                                rehoming.viewCnt
                        )
                )
                .from(rehoming)
                .leftJoin(rehoming.user,user)
                .where(rehoming.user.id.eq(userId))
                .orderBy(rehoming.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }

    @Override
    public Page<ProfileDto.CommentResponse> getMyComment(Long userId, Pageable pageable){
        QueryResults<ProfileDto.CommentResponse> results = queryFactory
                .select(
                        Projections.constructor(
                                ProfileDto.CommentResponse.class,
                                qna.qnaId,
                                comment.commentId,
                                qna.title,
                                comment.content,
                                comment.createdAt
                                )
                )
                .from(comment)
                .leftJoin(comment.user, user)
                .leftJoin(comment.qna, qna)
                .where(comment.user.id.eq(userId))
                .orderBy(comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }

//    public ProfileRepositoryImpl(JPAQueryFactory queryFactory) {
//        this.queryFactory = queryFactory;
//    }
    //내가 작성한 분양 게시글 조회

}
