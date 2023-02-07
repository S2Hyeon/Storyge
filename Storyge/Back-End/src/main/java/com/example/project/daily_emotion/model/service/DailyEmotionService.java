//package com.example.project.daily_emotion.model.service;
//
//import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
//import com.example.project.daily_emotion.model.entity.DailyEmotion;
//
//import java.util.Map;
//
//public interface DailyEmotionService {
//
//    //C
//    void insertDailyEmotion(DailyEmotionDto dailyEmotionDto);
//
//    //R
//    Long selectOneDailyEmotion(String nickname, String stringDate);
//
//    Map<Integer, String> selectDailyEmotions(String nickname, String stringDate);
//
//    //U
//    void updateDailyEmotion (Long userId, String emoticonName);
//
//    //D
//
//    void deleteDailyEmotion();
//
//    // DB-> 서버
//    default DailyEmotionDto toDto(DailyEmotion dailyEmotion){
//        return new DailyEmotionDto().builder()
//                .dailyId(dailyEmotion.getDailyId())
//                .emoticonName(dailyEmotion.getEmoticonName())
//                .createdAt(dailyEmotion.getCreatedAt())
//                .build();
//    }
//
//    //서버 -> DB
//    default DailyEmotion toEntity(DailyEmotionDto dailyEmotionDto){
//        return new DailyEmotion().builder()
//                .dailyId(dailyEmotionDto.getDailyId())
//                .emoticonName(dailyEmotionDto.getEmoticonName())
//                .createdAt(dailyEmotionDto.getCreatedAt())
//                .build();
//    }
//}
