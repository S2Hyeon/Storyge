package com.example.project.review.model.service;

import com.example.project.review.model.dto.ReviewRequsetDto;
import com.example.project.review.model.dto.ReviewResponseDto;
import com.example.project.review.model.dto.ReviewUpdateParam;

import java.util.List;

public interface ReviewService {

    void insertReview(ReviewRequsetDto reviewDto);
    List<ReviewResponseDto> selectAllReview(Long diaryId);
    Boolean updateReview(ReviewUpdateParam reviewUpdateParam);
    Boolean deleteReview(Long reviewId);



}
