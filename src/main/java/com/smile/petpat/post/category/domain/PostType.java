package com.smile.petpat.post.category.domain;


public enum PostType {
    QNA("QNA"),
    REHOMING("분양"),
    TRADE("중고거래"),
    ;

    private final String postType;

    PostType(String postType) {
        this.postType = postType;
    }

}
