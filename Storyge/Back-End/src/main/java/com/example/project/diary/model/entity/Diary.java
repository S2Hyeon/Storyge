package com.example.project.diary.model.entity;

import com.example.project.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String emoticonName;

    private String diaryContent;
    private int scope;
    private int update_cnt;
    private String analizedResult;

    private LocalDateTime createdAt;

    public void updateDiary(String emoticonName, int scope) {
        this.emoticonName = emoticonName;
        this.scope = scope;
    }

    public void updateDiaryContent(String emoticonName, String diaryContent, int scope, String analizedResult) {
        this.emoticonName = emoticonName;
        this.diaryContent = diaryContent;
        this.scope = scope;
        this.update_cnt = 1;    // 일기 내용이 변경되면 수정 횟수가 1이되어야 한다.
        this.analizedResult = analizedResult;
    }
}
