package com.smile.petpat.user.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
@SQLDelete(sql = "UPDATE TB_USER SET DELETED = true, NICKNAME =CONCAT('del_',FLOOR(RAND()*1000000)) WHERE USER_ID=? ")
@Where(clause = "DELETED = false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USER_EMAIL",unique = true)
    private String userEmail;
    @Column(name = "NICKNAME",unique = true)
    private String nickname;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PROFILE_IMG_PATH")
    private String profileImgPath;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "LOGIN_TYPE")
    private loginTypeEnum loginType;

    @Column(name = "DELETED")
    private Boolean deleted = Boolean.FALSE;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "USER_ROLE", length = 20)
    private UserRole userRole;

    @Builder
    public User(Long id, String userEmail, String nickname, String password,  String profileImgPath, loginTypeEnum loginType, UserRole userRole) {
        this.id = id;
        this.userEmail = userEmail;
        this.nickname = nickname;
        this.password = password;
        this.profileImgPath = profileImgPath;
        this.loginType = loginType;
        this.userRole = userRole;
    }

    public User(User socialUser) {
    }


     //유저 프로필 변경
    public void modifyProfile(UserCommand userCommand){
        this.nickname = userCommand.getNickname();
        this.profileImgPath = userCommand.getProfileImgPath();
        this.userEmail = userCommand.getUserEmail();
    }

    public void modifyPassword(String password){
        this.password =  password;
    }

    public enum loginTypeEnum{
        NORMAL("일반유저"),
        KAKAO("카카오유저"),
        GOOGLE("구글유저"),
        GITHUB("깃허브유저");

        private String userType;

        loginTypeEnum(String userType) {
            this.userType = userType;
        }
    }



}
