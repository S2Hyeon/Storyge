package com.example.project.review.model.entity;

import com.example.project.diary.model.entity.Diary;
import com.example.project.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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
    private Long reviewId;

    @Column(name="user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

    @Column(name="diary_id")
    private Long diaryId;

    @ManyToOne
    @JoinColumn(name="diary_id", insertable = false, updatable = false)
    private Diary diary;

    @Column(name = "review_content")
    private String reviewContent;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    private void createdAt(){
        createdAt = LocalDateTime.now();
    }

    public void updateReview(String reviewContent){
        this.reviewContent = reviewContent;
    }




}
