package com.example.project.recentdiary.model.entity;

import com.example.project.diary.model.entity.Diary;
import com.example.project.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @ManyToOne
    @Column(name="user_id")
    private User userId;

    @ManyToOne
    @Column(name="diary_id")
    private Diary diaryId;




}
