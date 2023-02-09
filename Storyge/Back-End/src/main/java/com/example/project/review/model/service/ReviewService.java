package com.example.project.review.model.service;

import com.example.project.review.model.dto.ReviewRequsetDto;
import com.example.project.review.model.dto.ReviewResponseDto;
import com.example.project.review.model.dto.ReviewUpdateParam;

import java.util.List;

public interface ReviewService {

    void insertReview(Long userId, ReviewRequsetDto reviewDto);
    List<ReviewResponseDto> selectAllReview(Long diaryId);
    Boolean updateReview(Long userId, ReviewUpdateParam reviewUpdateParam);
    Boolean deleteReview(Long userId, Long reviewId);



}
