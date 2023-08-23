package com.smile.petpat.jwt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.sql.SQLOutput;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RefreshTokenRepositoryTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void saveStrings() {
        // given
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "testKey";

        // when
        valueOperations.set(key,"hello");

        // then
        String value = valueOperations.get(key);
        System.out.println("hello ? "+value);
        assertThat(value).isEqualTo("hello");
    }
}