package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.ReadDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ReadDiaryRepository extends JpaRepository<ReadDiary, Long> {
    Optional<ReadDiary> findByUserIdAndAndRecentId(Long userId, Long recentId);
}
