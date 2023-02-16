package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.RecentDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RecentDiaryRepository extends JpaRepository<RecentDiary, Long> {

    Optional<RecentDiary> findByUserId(Long userId);

    Optional<RecentDiary> findByDiaryId(Long diaryId);

    void deleteByUserId(Long userId);

    void deleteByDiaryId(Long diaryId);

}
