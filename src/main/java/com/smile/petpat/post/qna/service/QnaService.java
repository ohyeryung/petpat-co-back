package com.smile.petpat.post.qna.service;

import com.smile.petpat.post.qna.domain.QnaCommand;
import com.smile.petpat.user.domain.User;

public interface QnaService {
    void addQna(QnaCommand Command);

    void listQna();

}
