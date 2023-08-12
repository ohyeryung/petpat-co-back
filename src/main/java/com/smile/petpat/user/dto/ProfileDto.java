package com.smile.petpat.user.dto;

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
}
