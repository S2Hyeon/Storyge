package com.example.project.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

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

    private String nickname;
    private String profileImg;
    private String refreshToken;
    private String role;
    private String provider;
    private String providerId;

    //회원 프로필 변경시 사용
    public void update(String nickname){
        this.nickname =nickname;
    }

    //소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트하고 기존 데이터는 그대로 보존
}
