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
@Table(name="read_diary")
public class ReadDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="read_id")
    private Long readId;

    @ManyToOne
    @Column(name="user_id")
    private User userId;

    @ManyToOne
    @Column(name="recent_id")
    private RecentDiary recentId;

}
