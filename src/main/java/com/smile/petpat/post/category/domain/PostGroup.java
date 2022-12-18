package com.smile.petpat.post.category.domain;


import com.smile.petpat.post.common.status.PostStatus;

import java.util.Arrays;
import java.util.List;

public enum PostGroup {
    QNA("QNA"),
    REHOMING("분양" ),
    TRADE("중고거래"),

    ;
    private final String description;

    PostGroup(String description) {
        this.description = description;
    }



    public String getDescription(){
        return description;
    }

}
