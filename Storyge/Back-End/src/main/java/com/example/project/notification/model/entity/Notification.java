//package com.example.project.notification.model.entity;
//
//import com.example.project.diary.model.entity.Diary;
//import com.example.project.user.model.entity.User;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.CreatedDate;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Getter
//public class Notification {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "noti_id")
//    private Long notiId;
//
//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User userId;
//
//    @ManyToOne
//    @JoinColumn(name="follow")
//    private User follow;
//
//    @Column(name="noti_type")
//    private String notiType;
//
//    @ManyToOne
//    @JoinColumn(name="diary_id")
//    private Diary diaryId;
//
//    @CreatedDate
//    @Column(name="created_at")
//    private LocalDateTime createdAt;
//
//    @PrePersist
//    private void createdAt(){
//        createdAt = LocalDateTime.now();
//    }
//
//}
