package com.smile.petpat.post.qna.domain;

import com.smile.petpat.user.domain.User;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class QnaDto {

    @Getter
    public static class CommonQna{

        @NotBlank(message = "제목은 필수입니다.")
        private String title;
        @NotBlank(message = "내용은 필수입니다.")
        private String content;

        public CommonQna(){

        }
        public CommonQna(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public QnaCommand toCommand(User user){
            return QnaCommand.builder()
                    .title(title)
                    .content(content)
                    .user(user)
                    .build();
        }
    }
}
