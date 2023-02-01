package com.example.project.daily_emotion.model.repository;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.daily_emotion.model.entity.DailyEmotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface DailyEmotionRepository extends JpaRepository<DailyEmotion, Long> {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Optional<DailyEmotion> findByIdAndCreatedAt(Long userId, Date createdAt);
}
