package com.smile.petpat.user.dto;

import lombok.Getter;
import lombok.Setter;

public class ProfileDto {

    @Getter
    @Setter
    public static class RehomingResponse{
        private Long rehomingId;
        private String title;
        private String createdAt;
        private int viewCnt;
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
    public static class CommentResponse{

    }
}
