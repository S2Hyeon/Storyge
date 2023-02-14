package com.example.project.recentdiary.model.dto;

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
@ApiModel(description = "최근 다이어리와 사용자 정보")
public class RecentDiaryResponseDto {

    @ApiModelProperty(value = "다이어리 id(pk)", example = "0")
    private Long diaryId;
    @ApiModelProperty(value = "사용자 id", example = "0")
    private Long userId;
    @ApiModelProperty(value = "사용자 닉네임")
    private String nickname;
    @ApiModelProperty(value = "사용자 프로필 이미지 url")
    private String profileImg;

}
