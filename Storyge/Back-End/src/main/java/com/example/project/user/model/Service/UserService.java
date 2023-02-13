package com.example.project.user.model.Service;

import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;

import java.util.List;

public interface UserService {

    //유저 정보 수정
    void updateUser(Long userId, String nickname, String profileUrl);

    UserDto selectOneUser(Long userId);

    // 사용자 검색
    List<UserDto> searchUser(String nickname, Long userId);

    boolean checkNickname(String nickname);

    default UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImg(user.getProfileImg()).build();

    }
}
