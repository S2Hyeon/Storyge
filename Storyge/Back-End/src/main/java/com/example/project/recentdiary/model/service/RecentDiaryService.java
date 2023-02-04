package com.example.project.recentdiary.model.service;

import com.example.project.diary.model.entity.Diary;
import com.example.project.user.model.entity.User;

public interface RecentDiaryService {
    void insertRecentDiary(User user, Diary diary); // 일기가 새로 올라오면 추가됨

}