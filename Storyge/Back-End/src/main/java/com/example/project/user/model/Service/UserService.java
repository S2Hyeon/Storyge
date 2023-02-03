package com.example.project.user.model.Service;

import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;

public interface UserService{

    default UserDto toDto(User user){
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profileImg(user.getProfileImg()).build();

    }
}
