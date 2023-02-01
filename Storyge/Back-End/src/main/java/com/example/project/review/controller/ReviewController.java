package com.example.project.review.controller;

import com.example.project.review.model.dto.ReviewRequsetDto;
import com.example.project.review.model.dto.ReviewResponseDto;
import com.example.project.review.model.dto.ReviewUpdateParam;
import com.example.project.review.model.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ReviewController {

    private final ReviewService reviewService;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";

    @PostMapping("/review")
    public ResponseEntity<String> insertReview(@RequestBody ReviewRequsetDto reviewRequsetDto){
        reviewService.insertReview(reviewRequsetDto);
        return new ResponseEntity(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/review/{diaryId}")
    public ResponseEntity<List<ReviewResponseDto>> selectAllReview(@PathVariable Long diaryId){
        List<ReviewResponseDto> reviewList = reviewService.selectAllReview(diaryId);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    @PutMapping("/review")
    public ResponseEntity<String> updateReview(@RequestBody ReviewUpdateParam reviewUpdateParam){
        reviewService.updateReview(reviewUpdateParam);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
