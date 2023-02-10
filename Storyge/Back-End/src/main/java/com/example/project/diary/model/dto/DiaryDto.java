package com.example.project.diary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDto {
    private Long diaryId;

    private Long userId;

    private String emoticonName;

    private String diaryContent;
    private Integer scope;
    private Integer updateCnt;
    private String analizedResult;
    private LocalDate createdAt;
}
