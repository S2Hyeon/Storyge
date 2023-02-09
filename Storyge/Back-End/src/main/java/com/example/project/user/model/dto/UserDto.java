package com.example.project.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "사용자 정보", description = "사용자 정보들이 담김")
public class UserDto {

    @ApiModelProperty(value = "사용자 Id(Pk값)", example = "20")
    private Long userId;
    @ApiModelProperty(value = "사용자 email",  example = "user1@abc.com")
    private String email;
    @ApiModelProperty(value = "사용자 nickname",  example = "user1")
    private String nickname;
    @ApiModelProperty(value = "사용자 프로필 이미지 url")
    private String profileImg;
}
