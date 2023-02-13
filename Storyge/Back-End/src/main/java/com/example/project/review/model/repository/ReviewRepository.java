package com.example.project.review.model.repository;

import com.example.project.diary.model.entity.Diary;
import com.example.project.review.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByDiaryIdOrderByCreatedAt(Long diaryId);

}
