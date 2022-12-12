package com.smile.petpat.post.category.domain;


public enum PostType {
    QNA("QNA","QNA"),
    REHOMING("REHOMING","분양"),
    TRADE("TRADE","중고거래"),
    ;
    private final String code;
    private final String description;

    PostType(String code, String description) {
        this.code = code;
        this.description = description;
    }


}
