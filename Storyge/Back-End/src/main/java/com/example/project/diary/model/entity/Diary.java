package com.example.project.diary.model.entity;

import com.example.project.user.model.entity.BaseTime;
import com.example.project.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Builder
@Getter
public class Diary extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    private String emoticonName;

    private String diaryContent;
    private Integer scope;
    private Integer updateCnt;
    private String analyzedResult;

    public void updateDiary(String emoticonName, String diaryContent, Integer scope, Integer updateCnt, String analyzedResult) {
        this.emoticonName = emoticonName;
        this.diaryContent = diaryContent;
        this.scope = scope;
        this.updateCnt = updateCnt;
        this.analyzedResult = analyzedResult;
    }

    public void updateScope(Integer scope) {
        this.scope = scope;
    }
}
