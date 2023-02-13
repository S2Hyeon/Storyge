package com.example.project.diary.model.repository;

import com.example.project.diary.model.dto.DailyEmotionStatistic;
import com.example.project.diary.model.dto.EmotionStatistic;

import java.time.LocalDate;
import java.util.List;


public interface DiaryCustomRepository {

    DailyEmotionStatistic dailyEmotionStatistic(Long userId, LocalDate date);

    List<EmotionStatistic> emotionStatistic(String period, LocalDate date, Long userId);
}
