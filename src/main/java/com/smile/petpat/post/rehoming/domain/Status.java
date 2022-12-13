package com.smile.petpat.post.rehoming.domain;

public enum Status {
    FINDING("분양 중"),
    RESERVING("예약 중"),
    MATCHED("분양 완료"),
 ;
    private final String Status;

    Status(String status) {
        this.Status = status;
    }
}
