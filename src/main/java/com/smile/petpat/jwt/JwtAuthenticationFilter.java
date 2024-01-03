package com.smile.petpat.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// header에 token이 있으면 토큰을 인증
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // header에서 token을 받아온다
        String token = tokenProvider.resolveToken(request);
        String method = request.getMethod();
        log.info("requestMethod : {}", method);
        String servletPath = request.getServletPath();
        if (token != null) {
            Authentication authentication = tokenProvider.getAuthentication(token, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 토큰이 없는 경우에 회원만 가능한 API 요청 시 에러 처리, 추후 거래게시판
        else if (servletPath.equals("/api/v1/rehoming") && method.equals("POST") || servletPath.startsWith("/api/v1/likes") ||
                 servletPath.startsWith("/api/v1/bookmarks") || servletPath.startsWith("/api/v1/rehoming/status")) {
            tokenProvider.tokenNullChk(response);
        }
        filterChain.doFilter(request, response);
    }
}
