package com.smile.petpat.config;

import com.smile.petpat.jwt.*;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;

    public WebSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**"
                , "/favicon.ico"
                , "/error");
    }

//    @Bean
//    @Order(1)
//    public SecurityFilterChain exceptionSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .requestMatchers((matchers) -> matchers.antMatchers("/h2-console/**"
//                        , "/favicon.ico"
//                        , "/error"))
//                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .requestCache().disable()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .cors().disable()
//                .csrf().disable()
//                ;
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .requestCache().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()
        ;

        http
                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/v1/user/**","h2-console/**","/api/v1/rehoming/**","/swagger-ui.html"
                        ,"/swagger-ui/**", "/swagger-ui")
                .permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
