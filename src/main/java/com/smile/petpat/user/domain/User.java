package com.smile.petpat.user.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "userId")
    private String userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "profileUrl")
    private String profileUrl;
    @Column(name = "profilePath")
    private String profilePath;

    // 후에 여러컬럼이나 테이블로 분리할지 생각해야함
    @Column
    private String location;

    public User() {
    }

    @Builder
    public User(Long id, String userId, String username, String password, String profileUrl, String profilePath, String location) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.profileUrl = profileUrl;
        this.profilePath = profilePath;
        this.location = location;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
