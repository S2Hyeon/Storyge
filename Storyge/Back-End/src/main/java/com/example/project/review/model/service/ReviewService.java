package com.example.project.review.model.service;

import com.example.project.review.model.dto.ReviewRequsetDto;
import com.example.project.review.model.dto.ReviewResponseDto;
import com.example.project.review.model.dto.ReviewUpdateParam;

import java.util.List;

public interface ReviewService {

    void insertReview(ReviewRequsetDto reviewDto);
    List<ReviewResponseDto> selectAllReview(long diaryId);
    void updateReview(ReviewUpdateParam reviewUpdateParam);
    void deleteReview(long reviewId);



}
