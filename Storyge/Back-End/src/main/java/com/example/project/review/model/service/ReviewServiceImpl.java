package com.example.project.review.model.service;

import com.example.project.review.model.dto.ReviewRequsetDto;
import com.example.project.review.model.dto.ReviewResponseDto;
import com.example.project.review.model.dto.ReviewUpdateParam;
import com.example.project.review.model.entity.Review;
import com.example.project.review.model.repository.ReviewRepository;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public void insertReview(ReviewRequsetDto reviewDto) {
        Optional<User> user =userRepository.findById(reviewDto.getUserId());
        Review review = Review.builder()
                .reviewContent(reviewDto.getReviewContent())
//                .diaryId(reviewDto.getDiaryId())
                .user(user.get())
                .build();
        reviewRepository.save(review);

    }

    @Override
    public List<ReviewResponseDto> selectAllReview(long diaryId) {

        List<Review> reviewList = reviewRepository.findByDiary(diaryId);
        List<ReviewResponseDto> reviewResponseList = new ArrayList<>();
        for(Review review: reviewList){
            User user = review.getUser();
            reviewResponseList.add(ReviewResponseDto.builder()
                    .reviewId(review.getReviewId())
                    .reviewContent(review.getReviewContent())
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .profileImg(user.getProfileImg())
                    .createdAt(review.getCreatedAt())
                    .build());
        }

        return reviewResponseList;
    }

    @Override
    public void updateReview(ReviewUpdateParam reviewUpdateParam) {

    }

    @Override
    public void deleteReview(long reviewId) {

    }
}
