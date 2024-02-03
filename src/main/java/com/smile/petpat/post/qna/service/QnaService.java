package com.smile.petpat.post.qna.service;

import com.smile.petpat.post.qna.domain.QnaCommand;
import com.smile.petpat.post.qna.domain.QnaInfo;
import com.smile.petpat.post.rehoming.dto.RehomingPagingDto;
import com.smile.petpat.user.domain.User;
import org.springframework.data.domain.Pageable;

public interface QnaService {
    void registerQna(QnaCommand qnaCommand, User user);

    QnaInfo.QnaPagingListInfo listQna(User user, Pageable pageable);
    QnaInfo.QnaDetail updateQna(User user, Long tradeId, QnaCommand qnaCommand);

}
