package com.example.project.daily_emotion.model.repository;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.daily_emotion.model.entity.DailyEmotion;
import com.querydsl.core.Fetchable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DailyEmotionRepository extends JpaRepository<DailyEmotion, Long> {

    Optional<DailyEmotion> findByUser_UserIdAndCreatedAt(Long userId, LocalDate createdAt);

    List<DailyEmotion> findAllByUser_UserIdAndCreatedAtBetween(Long userId, LocalDate firstOfMonth, LocalDate lastOfMonth);

}
