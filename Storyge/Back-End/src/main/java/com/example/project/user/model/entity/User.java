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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    //디비에 추가해야함
    private String password;
    private String nickname;
    private String profileImg;
    private String refreshToken;
    private String role = "ROLE_USER";

    private String provider;
    private String providerId;

    //회원 프로필&닉네임 변경시 사용
    public void update(String nickname, String profileImg) {
        this.nickname = nickname;
        this.profileImg = profileImg;
    }

}
