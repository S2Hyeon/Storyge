package com.example.project.daily_emotion.model.entity;

import com.example.project.user.model.entity.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String emoticonName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    public void updateDailyEmotion(String emoticonName) {
        this.emoticonName = emoticonName;
    }

}
