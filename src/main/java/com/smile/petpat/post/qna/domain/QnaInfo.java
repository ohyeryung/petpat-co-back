package com.smile.petpat.post.qna.domain;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.CalculateTime;
import com.smile.petpat.post.common.status.PostStatus;
import com.smile.petpat.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

public class QnaInfo {

    @Getter
    @ToString
    @AllArgsConstructor
    public static class QnaList{
        private Long qnaId;
        private Long userId;
        private String nickname;
        private String title;
        private String content;
        private String imagePath;
        private PostType postType;
        private int viewCnt;

        private String createAt;
        private String updatedAt;

        public QnaList(){
           
        }


        public QnaList(Long qnaId, Long userId, String nickname, String title, String content,
                       String imagePath, PostType postType,
                       int viewCnt, LocalDateTime createAt, LocalDateTime updatedAt) {
            this.qnaId = qnaId;
            this.userId = userId;
            this.nickname = nickname;
            this.title = title;
            this.content = content;
            this.imagePath = imagePath;
            this.postType = postType;
            this.viewCnt = viewCnt;
            this.createAt = CalculateTime.dateformatForPost(createAt);
            this.updatedAt = CalculateTime.dateformatForPost(updatedAt);
        }

    }

}
