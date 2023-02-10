package com.example.project.diary.model.entity;

import com.example.project.user.model.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Builder
@Getter
@Setter
public class Diary {

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
    private String analizedResult;

    private LocalDateTime createdAt;

    public void updateDiary(String emoticonName, String diaryContent, int scope, int updateCnt, String analizedResult) {
        this.emoticonName = emoticonName;
        this.diaryContent = diaryContent;
        this.scope = scope;
        this.updateCnt = updateCnt;
        this.analizedResult = analizedResult;
    }

    public void updateScope(int scope) {
        this.scope = scope;
    }
}
