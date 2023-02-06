package com.example.project.follow.model.dto;


import com.example.project.user.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowInfoDto {

    private Long followId;
    private UserDto userDto;

}
