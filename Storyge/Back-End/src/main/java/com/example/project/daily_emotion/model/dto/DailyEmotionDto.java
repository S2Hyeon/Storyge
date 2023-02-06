package com.example.project.daily_emotion.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
public class DailyEmotionDto {
    private Long dailyId;

    private Long userId;

    private String emoticonName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @QueryProjection
    public DailyEmotionDto(Long dailyId, Long userId, String emoticonName, LocalDate createdAt) {
        this.dailyId = dailyId;
        this.userId = userId;
        this.emoticonName = emoticonName;
        this.createdAt = createdAt;
    }
}
