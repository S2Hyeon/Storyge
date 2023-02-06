package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.ReadDiary;
import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadDiaryRepository extends JpaRepository<ReadDiary, Long> {
    ReadDiary findByRecentId(RecentDiary recentDiary);
    ReadDiary findByUserIdAndAndRecentId(User userId, RecentDiary recentId);
}
