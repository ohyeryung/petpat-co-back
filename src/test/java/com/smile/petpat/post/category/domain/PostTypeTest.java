package com.smile.petpat.post.category.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PostTypeTest {

    @DisplayName("description으로 PostType을 찾는다. ")
    @Test
    void findPostType() {
        // given
        PostType givenPostType = PostType.TRADE;
        String description = "중고거래";

        // when
        PostType result = PostType.find(description);

        // then
        assertThat(givenPostType).isEqualByComparingTo(result);
    }

    @DisplayName("description으로 PostType을 찾을 때 해당하는 postType이 없는 경우 예외를 발생시킨다.")
    @Test
    void notFoundPostType() {
        // given
        PostType givenPostType = PostType.TRADE;
        String description = "없는 타입";


        // when // then
        assertThatThrownBy(() -> PostType.find(description))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게시글 타입을 찾을 수 없습니다..");
    }
}