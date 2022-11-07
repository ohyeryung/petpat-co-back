package com.smile.petpat.category.domain;


public enum PostType {
    QnA("QNA"),
    REHOMING("분양"),
    TRADE("중고거래"),
    ;

    private final String postType;

    PostType(String postType) {
        this.postType = postType;
    }

}
