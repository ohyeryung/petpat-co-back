package com.smile.petpat.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import static com.smile.petpat.exception.ExceptionMessage.ILLEGAL_INVALID_TOKEN;

@Component
@RequiredArgsConstructor
public class TokenProvider{

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;
    private static final String TOKEN_PREFIX = "Bearer ";
    @Value("${jwt.secretkey}")
    String JWT_SECRET;

    // 토큰을 헤더에 담음
    public HttpHeaders headerToken(User user) {
        String token = jwtTokenUtils.generateJwtToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + " " + token);
        return headers;
    }

    // header에서 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(decodeUsername(token));
        return new UsernamePasswordAuthenticationToken(
                // The credentials that prove the principal is correct.
                userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String decodeUsername(String token) {
        DecodedJWT decodedJWT = isValidToken(token)
                .orElseThrow(() -> new IllegalArgumentException(ILLEGAL_INVALID_TOKEN));

        Date now = new Date();
        if (decodedJWT.getExpiresAt().before(now)) {
            throw new IllegalArgumentException(ILLEGAL_INVALID_TOKEN);
        }

        String username = decodedJWT
                .getClaim(JwtTokenUtils.CLAIM_USERID)
                .asString();

        return username;
    }

    // 토큰 유효성 검사
    public Optional<DecodedJWT> isValidToken(String token) {
        DecodedJWT jwt = null;

        try {
            JWTVerifier verifier = JWT
                    .require(generateAlgorithm(JWT_SECRET))
                    .build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }

    private static Algorithm generateAlgorithm(String secretKey) {
        return Algorithm.HMAC256(secretKey.getBytes());
    }
}