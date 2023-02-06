//package com.example.project.daily_emotion.model.entity;
//
//import com.example.project.diary.model.entity.User;
//import lombok.*;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Setter
//public class DailyEmotion {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long dailyId;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    private String emoticonName;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createdAt;
//
//    public void updateDailyEmotion(String emoticonName) {
//        this.emoticonName = emoticonName;
//    }
//
//}
