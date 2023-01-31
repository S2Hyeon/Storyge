package com.example.project.review.model.service;

import com.example.project.review.model.dto.ReviewDto;
import com.example.project.review.model.dto.ReviewGetDto;

import java.util.List;

public interface ReviewService {

    void insertReview(ReviewDto reviewDto);
    List<ReviewGetDto> selectAllReview(long diaryId);
    void updateReview();
    void deleteReview(long reviewId);



}
