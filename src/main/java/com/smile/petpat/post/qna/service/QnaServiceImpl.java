package com.smile.petpat.post.qna.service;

import com.smile.petpat.post.qna.domain.QnaCommand;
import com.smile.petpat.post.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService{

    private final QnaRepository qnaRepository;
    @Override
    public void addQna(QnaCommand command) {
        qnaRepository.save(command.toEntity());
    }

    @Override
    public void listQna() {
        qnaRepository.findAll();
    }

}
