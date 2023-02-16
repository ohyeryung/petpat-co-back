package com.smile.petpat.user.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_USER")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "NICKNAME")
    private String nickname;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PROFILE_IMG_PATH")
    private String profileImgPath;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "OAUTH_TYPE")
    private oauthEnum oauthType;

    // 후에 여러컬럼이나 테이블로 분리할지 생각해야함
    @Column(name = "LOCATION")
    private String location;

    @Builder
    public User(Long id, String userEmail, String nickname, String password,  String profileImgPath, oauthEnum oauthType,  String location) {
        this.id = id;
        this.userEmail = userEmail;
        this.nickname = nickname;
        this.password = password;
        this.profileImgPath = profileImgPath;
        this.oauthType = oauthType;
        this.location = location;
    }

    public User(User socialUser) {
    }

    public enum oauthEnum{
        NORMAL("일반유저"),
        KAKAO("카카오유저"),
        GOOGLE("구글유저");

        private String userType;

        oauthEnum(String userType) {
            this.userType = userType;
        }
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
