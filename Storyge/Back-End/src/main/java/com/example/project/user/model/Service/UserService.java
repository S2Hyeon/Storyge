package com.example.project.user.model.Service;

import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.dto.UserUpdateParam;
import com.example.project.user.model.entity.User;

public interface UserService {

    //유저 정보 수정
    void updateUser(UserUpdateParam param);

    UserDto selectOneUser(Long userId);

    default UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profileImg(user.getProfileImg()).build();

    }
}
