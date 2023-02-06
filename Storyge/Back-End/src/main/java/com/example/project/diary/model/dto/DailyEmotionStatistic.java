package com.example.project.diary.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DailyEmotionStatistic {

    private String emoticonName;

    private Long emoticonAmount;

    private LocalDateTime recentUpdate;

    @QueryProjection
    public DailyEmotionStatistic(String emoticonName, long emoticonAmount, LocalDateTime recentUpdate) {
        this.emoticonName = emoticonName;
        this.emoticonAmount = emoticonAmount;
        this.recentUpdate = recentUpdate;
    }
}
