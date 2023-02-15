package com.example.project.notification.model.entity;

import com.example.project.diary.model.entity.Diary;
import com.example.project.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noti_id")
    private Long notiId;

    @Column(name="user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    @Column(name="follow")
    private Long follow;

    @ManyToOne
    @JoinColumn(name="follow", insertable = false, updatable = false)
    private User followUser;

    @Column(name="noti_type")
    private String notiType;

    @Column(name="diary_id")
    private Long diaryId;

    @ManyToOne
    @JoinColumn(name="diary_id", insertable = false, updatable = false)
    private Diary diary;

    @Column(name="read_check")
    private int readCheck;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @PrePersist
    private void createdAt(){
        createdAt = LocalDateTime.now();
    }

    public void updateRead(int readCheck){
        this.readCheck = readCheck;
    }


}
