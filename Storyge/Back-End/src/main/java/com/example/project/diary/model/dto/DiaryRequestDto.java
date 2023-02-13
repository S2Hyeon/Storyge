package com.example.project.diary.model.dto;

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
public class DiaryRequestDto {

    @ApiModelProperty(example = "이모티콘 이름 ex) angry")
    private String emoticonName;

    private String diaryContent;

    @ApiModelProperty(example = "공개 범위 (0 => 비공개, 1 => 공개)")
    private Integer scope;

    @ApiModelProperty(example = "감정분석 결과")
    private String analizedResult;
}
