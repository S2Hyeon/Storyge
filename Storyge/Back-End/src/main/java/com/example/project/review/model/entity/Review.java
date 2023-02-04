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
    Long reviewId;

    @ManyToOne
    @JoinColumn(name="user_id")
    User userId;

    @ManyToOne
    @JoinColumn(name="diary_id")
    Diary diaryId;

    @Column(name = "review_content")
    String reviewContent;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
//    @DateTimeFormat(pattern ="yyyy.MM.dd HH:mm" )
    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @PrePersist
    private void createdAt(){
        createdAt = LocalDateTime.now();
    }

    public void updateReview(String reviewContent){
        this.reviewContent = reviewContent;
    }




}
