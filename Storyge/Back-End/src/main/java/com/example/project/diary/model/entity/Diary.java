package com.example.project.diary.model.entity;

import com.example.project.emoticon.model.entity.Emoticon;
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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "emoticon_id")
    private Long emoticonId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "emoticon_id")
    private Emoticon emoticon;

    private String diaryContent;
    private int scope;
    private int update_cnt;
    private String analizedResult;

    private LocalDateTime createdAt;

    public void updateDiary(Long emoticonId, int scope) {
        this.emoticonId = emoticonId;
        this.scope = scope;
    }
    public void updateDiaryContent (Long emoticonId, String diaryContent, int scope, String analizedResult){
        this.emoticonId = emoticonId;
        this.diaryContent = diaryContent;
        this.scope = scope;
        this.update_cnt = 1;    // 일기 내용이 변경되면 수정 횟수가 1이되어야 한다.
        this.analizedResult = analizedResult;
    }
}
