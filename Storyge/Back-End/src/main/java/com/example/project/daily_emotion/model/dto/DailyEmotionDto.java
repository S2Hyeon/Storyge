package com.example.project.daily_emotion.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyEmotionDto {
    private Long dailyId;

    private Long userId;

    private Long emotResult;

    private LocalDateTime createdAt;
}
