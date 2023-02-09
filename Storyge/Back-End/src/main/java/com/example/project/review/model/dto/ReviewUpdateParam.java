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
@ApiModel(value = "댓글 수정시 필요한 정보")
public class ReviewUpdateParam {

//    private Long diaryId;
    @ApiModelProperty(value = "수정할 댓글의 id(pk)", example = "0")
    private Long reviewId;
    @ApiModelProperty(value = "수정할 내용")
    private String reviewContent;

}
