package com.example.project.follow.model.dto;


import com.example.project.user.model.dto.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "사용자에게 표시할 팔로잉/팔로워의 정보")
public class FollowUserInfoDto {

    @ApiModelProperty(value = "사용자 userId(pk값)",example = "0")
    private Long userId;
    @ApiModelProperty(value = "사용자 프로필 사진 url")
    private String profileImg;
    @ApiModelProperty(value = "사용자 닉네임")
    private String nickname;

}
