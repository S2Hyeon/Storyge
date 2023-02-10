package com.example.project.diary.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryUpdateParam {

    private Long diaryId;

    @ApiModelProperty(example = "이모티콘 이름 ex) angry")
    private String emoticonName;

    private String diaryContent;

    @ApiModelProperty(example = "공개범위 ex) 0 => 비공개, 1 => 공개")
    private Integer scope;
    private String analizedResult;

}
