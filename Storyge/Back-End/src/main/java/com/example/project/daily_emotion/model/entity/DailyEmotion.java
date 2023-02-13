package com.example.project.daily_emotion.model.entity;

import com.example.project.user.model.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Builder
@Getter
@Setter
public class DailyEmotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    private String emoticonName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    public void updateDailyEmotion(String emoticonName) {
        this.emoticonName = emoticonName;
    }

}
