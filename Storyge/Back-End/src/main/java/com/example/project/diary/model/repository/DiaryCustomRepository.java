package com.example.project.diary.model.repository;

import com.example.project.diary.model.dto.DailyEmotionStatistic;

import java.time.LocalDateTime;

public interface DiaryCustomRepository {

    DailyEmotionStatistic dailyEmotionStatistic(Long user_id, LocalDateTime date);
}
