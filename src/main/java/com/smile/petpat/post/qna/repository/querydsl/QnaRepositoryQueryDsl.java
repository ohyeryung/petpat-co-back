package com.smile.petpat.post.qna.repository.querydsl;

import com.smile.petpat.post.qna.domain.QnaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QnaRepositoryQueryDsl {
    List<QnaInfo.QnaList> qnaList(Long userId);

    Page<QnaInfo.QnaList> qnaListPaging(Long userId, Pageable pageable);

}
