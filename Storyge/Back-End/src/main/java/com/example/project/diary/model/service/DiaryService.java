package com.example.project.diary.model.service;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.entity.Diary;

import java.util.List;
import java.util.Optional;

public interface DiaryService {

    //C
    void insertDiary(DiaryDto diaryDto);

    //R

    Optional<DiaryDto> selectOneDiary(Long diaryId);
    List<DiaryDto> selectDailyDiaries(String nickname, String stringDate);

    //U
    void updateDiary (DiaryUpdateParam param);

    //D
    void deleteDiary(Long diaryId);

    // DB-> 서버
    default DiaryDto toDto(Diary diary){
        return new DiaryDto().builder()
                .diaryId(diary.getDiaryId())
                .emoticonName(diary.getEmoticonName())
                .diaryContent(diary.getDiaryContent())
                .scope(diary.getScope())
                .update_cnt(diary.getUpdate_cnt())
                .analizedResult(diary.getAnalizedResult())
                .build();
    }

    //서버 -> DB
    default Diary toEntity(DiaryDto diaryDto){
        return new Diary().builder()
                .emoticonName(diaryDto.getEmoticonName())
                .diaryContent(diaryDto.getDiaryContent())
                .scope(diaryDto.getScope())
                .update_cnt(diaryDto.getUpdate_cnt())
                .analizedResult(diaryDto.getAnalizedResult())
                .build();
    }

    default DailyEmotionDto toDailyEmotionDto(DiaryDto diaryDto) {
        return new DailyEmotionDto().builder()
                .userId(diaryDto.getUserId())
                .emoticonName(diaryDto.getEmoticonName())
                .createdAt(diaryDto.getCreatedAt())
                .build();
    }
}
