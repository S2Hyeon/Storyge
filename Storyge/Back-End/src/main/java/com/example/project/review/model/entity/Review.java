package com.example.project.review.model.entity;

import com.example.project.diary.model.entity.Diary;
import com.example.project.user.model.entity.BaseTime;
import com.example.project.user.model.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Review extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "diary_id")
    private Long diaryId;

    @ManyToOne
    @JoinColumn(name = "diary_id", insertable = false, updatable = false)
    private Diary diary;

    @Column(name = "review_content")
    private String reviewContent;

    @Builder
    public Review(Long reviewId, Long userId, User user, Long diaryId, Diary diary, String reviewContent) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.user = user;
        this.diaryId = diaryId;
        this.diary = diary;
        this.reviewContent = reviewContent;
    }

    public void updateReview(String reviewContent) {
        this.reviewContent = reviewContent;
    }


}
