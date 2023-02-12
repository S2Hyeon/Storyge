package com.example.project.recentdiary.model.entity;

import com.example.project.diary.model.entity.Diary;
import com.example.project.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="recent_diary")
public class RecentDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recent_id")
    private Long recentId;

    @Column(name="user_id")
    private Long userId;


    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    @Column(name="diary_id")
    private Long diaryId;

    @ManyToOne
    @JoinColumn(name="diary_id", insertable = false, updatable = false)
    private Diary diary;

    @Column(name="ends_at")
    private LocalDateTime endsAt;

}
