//package com.example.project.daily_emotion.model.repository;
//
//import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
//import com.example.project.daily_emotion.model.entity.DailyEmotion;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Repository
//public interface DailyEmotionRepository extends JpaRepository<DailyEmotion, Long> {
//
//    Long countByUser_UserIdAndCreatedAt(Long userId, LocalDateTime createdAt);
//
//    List<DailyEmotionDto> findByUser_UserIdAndCreatedAt(Long userId, LocalDateTime createdAt);
//}
