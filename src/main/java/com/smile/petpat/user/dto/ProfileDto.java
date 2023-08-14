package com.smile.petpat.user.dto;

import com.smile.petpat.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class ProfileDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class RehomingResponse{
        private Long rehomingId;
        private String rehomingImgUrl;
        private String title;
        private LocalDateTime createdAt;
        private Integer viewCnt;
    }

    @Getter
    @Setter
    public static class TradeResponse{


    }

    @Getter
    @Setter
    public static class QnaResponse{

    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class CommentResponse{
        private Long QnAId;
        private Long commentId;
        private String title;
        private String commentContent;
        private LocalDateTime createdAt;
    }

    @Getter
    @Setter
    public static class ProfileResponse{
        private Long userId;
        private String userEmail;
        private String nickname;
        private String profileImgUrl;

        public ProfileResponse(User user){
            this.userId = user.getId();
            this.userEmail = user.getUserEmail();
            this.nickname = user.getNickname();
            this.profileImgUrl = user.getProfileImgPath();

        }
    }
}
