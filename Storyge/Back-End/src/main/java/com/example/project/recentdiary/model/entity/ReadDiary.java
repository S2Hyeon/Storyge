package com.example.project.recentdiary.model.entity;

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

    @Column(name="user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    @Column(name="recent_id")
    private Long recentId;

    @ManyToOne
    @JoinColumn(name="recent_id", insertable = false, updatable = false)
    private RecentDiary recentDiary;

}
