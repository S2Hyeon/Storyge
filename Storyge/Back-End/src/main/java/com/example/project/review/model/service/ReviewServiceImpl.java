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

    // 댓글 입력
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

    // 댓글 가져오기
    @Override
    public List<ReviewResponseDto> selectAllReview(Long diaryId) {

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

    // 댓글 수정
    @Override
    public void updateReview(ReviewUpdateParam reviewUpdateParam, Long userId) {

        //작성자와 현재 수정하려는 사람이 일치하는지 확인해야 한다
        Long reviewId = reviewUpdateParam.getReviewId(); //현재 작성한 댓글의 번호
        Review review = reviewRepository.findById(reviewId).orElse(null); //댓글 정보 select
        Long reviewUser = review.getUser().getUserId(); // 댓글 작성자 가져오기
        if(reviewUser==userId){
            review.updateReview(review.getReviewContent()); // 같다면 수정하기
        }
    }

    // 댓글 삭제
    @Override
    public void deleteReview(Long reviewId, Long userId) {
        //작성자와 현재 삭제하려는 사람이 일치하는지 확인해야 한다
        Optional<Review> review = reviewRepository.findById(reviewId).ofNullable(null);
        Long reviewUser = review.get().getUser().getUserId();
        if(userId==reviewUser){
            reviewRepository.deleteById(reviewId);
        }

    }
}
