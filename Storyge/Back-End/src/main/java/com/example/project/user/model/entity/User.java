package com.example.project.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    //디비에 추가해야함
    private String name;
    private String nickname;
    private String profileImg;
    private String refreshToken;
    private String role;

    private String provider;
    private String providerId;

    //회원 프로필&닉네임 변경시 사용
    public void update(String nickname, String profileImg) {
        this.nickname = nickname;
        this.profileImg = profileImg;
    }

    @Builder
    public User(Long userId, String email, String name, String nickname, String profileImg, String refreshToken, String role, String provider, String providerId) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.refreshToken = refreshToken;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}
