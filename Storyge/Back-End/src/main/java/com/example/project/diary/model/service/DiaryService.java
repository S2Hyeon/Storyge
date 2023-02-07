package com.example.project.diary.model.service;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.entity.Diary;

import java.util.List;

public interface DiaryService {

    //C
    boolean insertDiary(DiaryDto diaryDto);

    //R

    DiaryDto selectOneDiary(Long diaryId);
    List<DiaryDto> selectDailyDiaries(String nickname, String stringDate);

    //U
    boolean updateDiary (DiaryUpdateParam param);

    //D
    boolean deleteDiary(Long diaryId);

    // DB-> 서버
    default DiaryDto toDto(Diary diary){
        return DiaryDto.builder()
                .diaryId(diary.getDiaryId())
                .userId(diary.getUser().getUserId())
                .emoticonName(diary.getEmoticonName())
                .diaryContent(diary.getDiaryContent())
                .scope(diary.getScope())
                .update_cnt(diary.getUpdateCnt())
                .analizedResult(diary.getAnalizedResult())
                .createdAt(diary.getCreatedAt().toLocalDate())
                .build();
    }

    //서버 -> DB
//    default Diary toEntity(DiaryDto diaryDto){
//        return new Diary().builder()
//                .user(diaryDto.getUserId())
//                .emoticonName(diaryDto.getEmoticonName())
//                .diaryContent(diaryDto.getDiaryContent())
//                .scope(diaryDto.getScope())
//                .update_cnt(diaryDto.getUpdate_cnt())
//                .analizedResult(diaryDto.getAnalizedResult())
//                .build();
//    }

    default DailyEmotionDto toDailyEmotionDto(DiaryDto diaryDto) {
        return DailyEmotionDto.builder()
                .userId(diaryDto.getUserId())
                .emoticonName(diaryDto.getEmoticonName())
                .createdAt(diaryDto.getCreatedAt())
                .build();
    }
}
