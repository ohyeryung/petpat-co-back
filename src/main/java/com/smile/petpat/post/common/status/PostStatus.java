package com.smile.petpat.post.common.status;

public enum PostStatus {
    REHOMING_FINDING("분양 중"),
    REHOMING_RESERVING("분양 예약중"),
    REHOMING_MATCHED("분양 완료"),
    TRADE_FINDING("판매 중"),
    TRADE_RESERVING("판매 예약중"),
    TRADE_COMPLETED("판매 완료")
 ;
    private final String Status;

    PostStatus(String status) {
        this.Status = status;
    }
    public String getStatus(){
        return Status;
    }
}
