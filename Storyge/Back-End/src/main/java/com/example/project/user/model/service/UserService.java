package com.example.project.user.model.service;

import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.dto.UserUpdateParam;
import com.example.project.user.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService {

    //access-token 받아오기
//    OauthToken getAccessToken(String code);
    //등록 && token 받아오기
    String saveUserAndGetToken(String token);

    //토큰 생성
    String createToken(UserDto userDto);

    //한명 불러오기
    Optional<User> getUser(HttpServletRequest request);

    //유저 수정
    void modifyUser(UserUpdateParam param);

    default UserDto toDto(User user){
        return UserDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .profileImg(user.getProfileImg())
                .build();
    }
}
