package com.example.project.review.model.entity;

import com.example.project.diary.model.entity.Diary;
import com.example.project.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    Long reviewId;

    @ManyToOne
    @JoinColumn(name="user_id")
    User userId;

    @ManyToOne
    @JoinColumn(name="diary_id")
    Diary diaryId;

    @Column(name = "review_content")
    String reviewContent;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    public void updateReview(String reviewContent){
        reviewContent = this.reviewContent;
    }

}
