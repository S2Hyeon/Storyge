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
public class DiaryDto {
    private Long diaryId;

    private Long userId;

    @ApiModelProperty(example = "이모티콘 이름 ex) angry")
    private String emoticonName;

    private String diaryContent;
    @ApiModelProperty(example = "공개 범위 (0 => 비공개, 1 => 공개)")
    private Integer scope;
    @ApiModelProperty(example = "업데이트 횟수 (0 => 수정한 적 없음, 1 => 수정기록 있음")
    private Integer updateCnt;

    private String analizedResult;
    @ApiModelProperty(example = "작성날짜 ex) 2023-02-07")
    private LocalDateTime createdAt;
}
