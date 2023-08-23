package com.smile.petpat.jwt;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RefreshTokenRepository {

    private RedisTemplate redisTemplate;

    public RefreshTokenRepository(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(final RefreshToken refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getRefreshToken(), refreshToken.getUserEmail());
        redisTemplate.expire(refreshToken.getRefreshToken(), 1L, TimeUnit.MINUTES);
    }

    public Optional<RefreshToken> findByEmail(final String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
         String userEmail = valueOperations.get(refreshToken);

        if (Objects.isNull(userEmail)) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(refreshToken, userEmail));
    }
}
