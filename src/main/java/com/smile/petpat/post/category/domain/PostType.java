package com.smile.petpat.post.category.domain;


import org.springframework.expression.spel.ast.Operator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PostType {
    QNA("QNA"),
    REHOMING("분양" ),
    TRADE("중고거래"),

    ;
    private final String description;

    private static final Map<String, PostType> POST_TYPE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(PostType::getDescription, Function.identity())));

    PostType(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public static PostType find(String description) {
        if (POST_TYPE_MAP.containsKey(description)) {
            return POST_TYPE_MAP.get(description);
        }
        throw new IllegalArgumentException("게시글 타입을 찾을 수 없습니다..");
    }



}
