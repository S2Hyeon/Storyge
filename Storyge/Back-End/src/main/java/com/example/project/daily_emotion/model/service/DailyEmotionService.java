package com.example.project.daily_emotion.model.service;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.daily_emotion.model.entity.DailyEmotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyEmotionService {

    //C
    boolean insertDailyEmotion(DailyEmotionDto dailyEmotionDto);

    //R
    Optional<DailyEmotion> selectOneDailyEmotion(Long userId, LocalDate date);

    List<DailyEmotionDto> selectAllDailyEmotion(Long userId, String stringDate);

    //U
    void updateDailyEmotion(Long userId, LocalDate date, String emoticonName);

    //D
    void deleteDailyEmotion(Long userId, LocalDate date);

    // DB-> 서버
    default DailyEmotionDto toDto(DailyEmotion dailyEmotion) {
        return DailyEmotionDto.builder()
                .dailyId(dailyEmotion.getDailyId())
                .userId(dailyEmotion.getUser().getUserId())
                .emoticonName(dailyEmotion.getEmoticonName())
                .createdAt(dailyEmotion.getCreatedAt())
                .build();
    }

    //서버 -> DB
    default DailyEmotion toEntity(DailyEmotionDto dailyEmotionDto) {
        return DailyEmotion.builder()
                .dailyId(dailyEmotionDto.getDailyId())
                .emoticonName(dailyEmotionDto.getEmoticonName())
                .createdAt(dailyEmotionDto.getCreatedAt())
                .build();
    }
}
