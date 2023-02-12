package com.example.project.diary.model.service;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.diary.model.dto.DiaryRequestDto;
import com.example.project.diary.model.dto.DiaryResponseDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.dto.EmotionStatistic;
import com.example.project.diary.model.entity.Diary;

import java.util.List;
import java.util.Optional;

public interface DiaryService {

    //C
    boolean insertDiary(Long userId, DiaryRequestDto diaryDto);

    //R

    Optional<DiaryResponseDto> selectOneDiary(Long diaryId);

    List<DiaryResponseDto> selectAllDailyDiary(Long userId, String stringDate);

    int selectDiaryCount(Long userId);

    List<EmotionStatistic> selectEmotionStatistic(String period, String stringDate, Long userId);

    //U
    boolean updateDiary(Long userId, DiaryUpdateParam param);

    boolean updateScope(Long userId, Long diaryId, int scope);

    //D
    boolean deleteDiary(Long userId, Long diaryId);

    // DB-> 서버
    default DiaryResponseDto toResponseDto(Diary diary) {
        return DiaryResponseDto.builder()
                .diaryId(diary.getDiaryId())
                .userId(diary.getUser().getUserId())
                .emoticonName(diary.getEmoticonName())
                .diaryContent(diary.getDiaryContent())
                .scope(diary.getScope())
                .updateCnt(diary.getUpdateCnt())
                .analizedResult(diary.getAnalizedResult())
                .createdAt(diary.getCreatedAt())
                .build();
    }

    //서버 -> DB
    default Diary toEntity(DiaryRequestDto diaryRequestDto) {
        return Diary.builder()
                .emoticonName(diaryRequestDto.getEmoticonName())
                .diaryContent(diaryRequestDto.getDiaryContent())
                .scope(diaryRequestDto.getScope())
                .analizedResult(diaryRequestDto.getAnalizedResult())
                .build();
    }

    default DailyEmotionDto toDailyEmotionDto(Diary diary) {
        return DailyEmotionDto.builder()
                .userId(diary.getUserId())
                .emoticonName(diary.getEmoticonName())
                .createdAt(diary.getCreatedAt().toLocalDate())
                .build();
    }
}
