package com.smile.petpat.post.qna.domain;

import com.smile.petpat.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QnaCommand {
    private String title;
    private String content;
    private User user;

    public QnaCommand(){

    }
    public QnaCommand(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    /*
    Entity 안에서는 @Builder 사용불가
    임시 대체
     */
    public Qna toEntity(){
        return Qna.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }

}
