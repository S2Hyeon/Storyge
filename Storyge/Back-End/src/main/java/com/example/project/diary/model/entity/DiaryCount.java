package com.example.project.diary.model.entity;

import com.example.project.user.model.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DiaryCount {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    private int diaryCnt;

    public void updateCount(int diaryCnt) {
        this.diaryCnt = diaryCnt;
    }
}
