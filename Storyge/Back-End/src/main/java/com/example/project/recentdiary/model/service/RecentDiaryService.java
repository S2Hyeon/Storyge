package com.example.project.recentdiary.model.service;

import com.example.project.diary.model.entity.Diary;
import com.example.project.recentdiary.model.dto.RecentDiaryResponseDto;
import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.user.model.entity.User;

import java.util.List;

public interface RecentDiaryService {
    void insertRecentDiary(User user, Diary diary); // 일기가 새로 올라오면 추가됨
    void insertReadDiary(User user, RecentDiary recentDiary); // 읽은 일기 표시

    List<RecentDiaryResponseDto> selectAllRecentDiary();

}