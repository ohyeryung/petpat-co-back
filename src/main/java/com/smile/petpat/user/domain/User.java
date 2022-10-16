package com.smile.petpat.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
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
    public User(Long userId, String username, String password, String profileUrl, String profilePath, String location) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.profileUrl = profileUrl;
        this.profilePath = profilePath;
        this.location = location;
    }
}
