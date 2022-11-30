package com.smile.petpat.user.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.awt.font.MultipleMaster;
import java.util.Collection;

@Getter
@Builder
@Entity
@Table(name = "TB_USER") // 테이블 명 통일하면 어떨까 싶어서 수정함! (users -> TB_USER)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;
    @Column(name = "profileImgPath")
    private String profileImgPath;

    // 후에 여러컬럼이나 테이블로 분리할지 생각해야함
    @Column
    private String location;

    public User() {
    }

    @Builder
    public User(Long id, String userEmail, String nickname, String password,  String profileImgPath, String location) {
        this.id = id;
        this.userEmail = userEmail;
        this.nickname = nickname;
        this.password = password;
        this.profileImgPath = profileImgPath;
        this.location = location;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
