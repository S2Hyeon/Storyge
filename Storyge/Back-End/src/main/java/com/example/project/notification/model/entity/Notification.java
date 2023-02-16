package com.example.project.notification.model.entity;

import com.example.project.diary.model.entity.Diary;
import com.example.project.user.model.entity.BaseTime;
import com.example.project.user.model.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@DynamicInsert
@Getter
public class Notification extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noti_id")
    private Long notiId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "follow")
    private Long follow;

    @ManyToOne
    @JoinColumn(name = "follow", insertable = false, updatable = false)
    private User followUser;

    @Column(name = "noti_type")
    private String notiType;

    @Column(name = "diary_id")
    private Long diaryId;

    @ManyToOne
    @JoinColumn(name = "diary_id", insertable = false, updatable = false)
    private Diary diary;

    @Column(name = "read_check")
    private int readCheck;

    @Builder
    public Notification(Long notiId, Long userId, User user, Long follow, User followUser, String notiType, Long diaryId, Diary diary, int readCheck) {
        this.notiId = notiId;
        this.userId = userId;
        this.user = user;
        this.follow = follow;
        this.followUser = followUser;
        this.notiType = notiType;
        this.diaryId = diaryId;
        this.diary = diary;
        this.readCheck = readCheck;
    }

    public void updateRead(int readCheck) {
        this.readCheck = readCheck;
    }


}
