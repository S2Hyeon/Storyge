package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.user.model.entity.User;

import java.util.List;

public interface RecentDiaryCustomRepository {

    List<RecentDiary> selectAllRecentDiaryByFollowing(Long userId);
}
