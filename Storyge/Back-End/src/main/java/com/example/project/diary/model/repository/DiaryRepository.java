package com.example.project.diary.model.repository;

import com.example.project.diary.model.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByUser_UserIdAndCreatedAtBetween(Long userId, LocalDateTime startTimeOfDay, LocalDateTime endTimeOfDay);
}
