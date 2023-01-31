package com.example.project.diary.model.dto;

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

    private Long emoticonId;

    private String diaryContent;
    private int scope;
    private int update_cnt;
    private String analizedResult;
    private LocalDateTime createdAt;
}
