package com.example.project.daily_emotion.model.service;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.daily_emotion.model.entity.DailyEmotion;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DailyEmotionService {

    //C
    void insertDailyEmotion(DailyEmotionDto dailyEmotionDto);

    //R
    DailyEmotionDto selectOneDailyEmotion(Long userId, Date date);

    Map<Integer, File> selectDailyEmotions(String nickname, Date date);

    //U
    void updateDailyEmotion (String nickname, DailyEmotionDto dailyEmotionDto);

    //D

    void deleteDailyEmotion();

    // DB-> 서버
    default DailyEmotionDto toDto(DailyEmotion dailyEmotion){
        return new DailyEmotionDto().builder()
                .dailyId(dailyEmotion.getDailyId())
                .userId(dailyEmotion.getUserId())
                .emotResult(dailyEmotion.getEmotResult())
                .createdAt(dailyEmotion.getCreatedAt())
                .build();
    }

    //서버 -> DB
    default DailyEmotion toEntity(DailyEmotionDto dailyEmotionDto){
        return new DailyEmotion().builder()
                .dailyId(dailyEmotionDto.getDailyId())
                .userId(dailyEmotionDto.getUserId())
                .emotResult(dailyEmotionDto.getEmotResult())
                .createdAt(dailyEmotionDto.getCreatedAt())
                .build();
    }
}
