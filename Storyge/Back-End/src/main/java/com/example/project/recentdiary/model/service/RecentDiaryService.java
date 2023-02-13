package com.example.project.recentdiary.model.service;

import com.example.project.recentdiary.model.dto.RecentDiaryResponseDto;

import java.util.List;

public interface RecentDiaryService {
    void insertRecentDiary(Long user, Long diary); // 일기가 새로 올라오면 추가됨
    Boolean insertReadDiary(Long userId, Long diaryId); // 읽은 일기 표시

    List<RecentDiaryResponseDto> selectAllRecentDiary(Long userId);

}