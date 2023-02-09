package com.example.project.diary.model.entity;

import com.example.project.user.model.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
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

    private Integer diaryCnt;

    public void updateCount(int diaryCnt) {
        this.diaryCnt = diaryCnt;
    }
}
