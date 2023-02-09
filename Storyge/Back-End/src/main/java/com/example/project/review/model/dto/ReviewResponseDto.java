package com.example.project.review.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "댓글 조회시 반환하는 정보")
public class ReviewResponseDto {

    @ApiModelProperty(value = "댓글의 id(pk)")
    private Long reviewId;
    @ApiModelProperty(value = "댓글을 입력한 사람의 id(pk)")
    private Long userId;
    @ApiModelProperty(value = "댓글을 입력한 사람의 닉네임")
    private String nickname;
    @ApiModelProperty(value = "댓글을 입력한 사람의 프로필 이미지 url")
    private String profileImg;
    @ApiModelProperty(value = "댓글 내용")
    private String reviewContent;
    @ApiModelProperty(value = "댓글을 입력한 시간 yyyy.MM.dd HH:mm 형식으로 반환")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
}
