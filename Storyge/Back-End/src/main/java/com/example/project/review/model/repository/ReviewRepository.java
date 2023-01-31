package com.example.project.review.model.repository;

import com.example.project.review.model.dto.ReviewResponseDto;
import com.example.project.review.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByDiary(long diaryId);

}
