package com.example.project.diary.model.service;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.dto.EmotionStatistic;
import com.example.project.diary.model.entity.Diary;

import java.util.List;

public interface DiaryService {

    //C
    boolean insertDiary(DiaryDto diaryDto);

    //R

    DiaryDto selectOneDiary(Long diaryId);

    List<DiaryDto> selectAllDailyDiary(Long userId, String stringDate);

    int selectDiaryCount(Long userId);

    List<EmotionStatistic> selectEmotionStatistic(String period, String stringDate, Long userId);

    //U
    boolean updateDiary(Long userId, DiaryUpdateParam param);

    boolean updateScope(Long userId, Long diaryId, int scope);

    //D
    boolean deleteDiary(Long userId, Long diaryId);

    // DB-> 서버
    default DiaryDto toDto(Diary diary) {
        return DiaryDto.builder()
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
    default Diary toEntity(DiaryDto diaryDto) {
        return Diary.builder()
                .userId(diaryDto.getUserId())
                .emoticonName(diaryDto.getEmoticonName())
                .diaryContent(diaryDto.getDiaryContent())
                .scope(diaryDto.getScope())
                .analizedResult(diaryDto.getAnalizedResult())
                .build();
    }

    default DailyEmotionDto toDailyEmotionDto(DiaryDto diaryDto) {
        return DailyEmotionDto.builder()
                .userId(diaryDto.getUserId())
                .emoticonName(diaryDto.getEmoticonName())
                .createdAt(diaryDto.getCreatedAt().toLocalDate())
                .build();
    }
}
