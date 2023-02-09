package com.example.project.review.model.dto;

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
@ApiModel(value = "댓글 입력시 필요한 정보")
public class ReviewRequsetDto {

//    private Long userId;
    @ApiModelProperty(value = "댓글을 입력한 일기의 id(pk)")
    private Long diaryId;
    @ApiModelProperty(value = "입력한 댓글의 내용")
    private String reviewContent;

}
