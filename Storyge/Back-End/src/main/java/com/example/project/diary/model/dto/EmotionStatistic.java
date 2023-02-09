package com.example.project.diary.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmotionStatistic {
    private String emoticonName;

    private Long emoticonAmount;

    @QueryProjection
    public EmotionStatistic(String emoticonName, long emoticonAmount) {
        this.emoticonName = emoticonName;
        this.emoticonAmount = emoticonAmount;
    }
}
