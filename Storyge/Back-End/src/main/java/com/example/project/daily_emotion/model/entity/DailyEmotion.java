package com.example.project.daily_emotion.model.entity;

import com.example.project.diary.model.entity.User;
import com.example.project.emoticon.model.entity.Emoticon;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DailyEmotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "emoticon_id")
    private Long emotResult;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "emoticon_id")
    private Emoticon emoticon;

    private LocalDateTime createdAt;

    public void updateDailyEmotion(Long emotResult) {
        this.emotResult = emotResult;
    }

}
