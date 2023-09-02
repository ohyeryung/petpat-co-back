package com.smile.petpat.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.smile.petpat.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

    private final RefreshTokenManager refreshTokenManager;
    private static final int DAY = 24 * 60 * 60;
    // JWT 토큰의 유효기간: 3일 (단위: milliseconds)
    private static final int JWT_TOKEN_VALID_MILLI_SEC = 3 * DAY * 1000;
    public static final String CLAIM_USERID = "USER_ID";
//    public static final String CLAIM_OAUTH = "OAUTH_INFO";
    public static final String ISSUER = "petpat";

    @Value("${jwt.secretkey}")
    String JWT_SECRET;

    public String generateJwtToken(User user) {
        String token = null;

        token = JWT.create()
                .withIssuer(ISSUER)
                .withPayload(createClaims(user))
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                .sign(generateAlgorithm(JWT_SECRET));

        return token;
    }

    public String generateRefreshToken(String userEmail){
      return   refreshTokenManager.saveRefreshToken(userEmail);
    }

    private Map<String, Object> createClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USERID, user.getUserEmail());

        return claims;
    }

    private static Algorithm generateAlgorithm(String secretKey) {
        return Algorithm.HMAC256(secretKey.getBytes());
    }
}
