package com.example.project.diary.model.repository;

import com.example.project.diary.model.dto.DailyEmotionStatistic;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


public interface DiaryCustomRepository {

    DailyEmotionStatistic dailyEmotionStatistic(Long userId, LocalDate date);
}
