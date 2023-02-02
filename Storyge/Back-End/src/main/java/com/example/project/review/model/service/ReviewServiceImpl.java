package com.example.project.review.model.service;

import com.example.project.diary.model.entity.Diary;
import com.example.project.diary.model.repository.DiaryRepository;
import com.example.project.notification.model.dto.NotificationReviewDto;
import com.example.project.notification.model.service.NotificationService;
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
    private final DiaryRepository diaryRepository;

    private final NotificationService notificationService;

    // 댓글 입력
    @Override
    public void insertReview(ReviewRequsetDto reviewDto) {
        User user =userRepository.findById(reviewDto.getUserId()).orElse(null);
        Diary diary = diaryRepository.findById(reviewDto.getDiaryId()).orElse(null);

        Review review = Review.builder()
                .reviewContent(reviewDto.getReviewContent())
                .diaryId(diary)
                .userId(user)
                .build();
        reviewRepository.save(review);

        User diaryUser = userRepository.findById(diary.getUserId()).orElse(null);

        if(diaryUser.getUserId()!=user.getUserId()) {
            // 알림 센터에 추가
            notificationService.insertReviewNotificatino(NotificationReviewDto.builder()
                    .userId(diaryUser.getUserId())
                    .follow(user.getUserId())
                    .diaryId(diary.getDiaryId())
                    .build());
        }

    }

    // 댓글 가져오기
    @Override
    public List<ReviewResponseDto> selectAllReview(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElse(null);
        List<Review> reviewList = reviewRepository.findByDiaryId(diary);
        List<ReviewResponseDto> reviewResponseList = new ArrayList<>();
        for(Review review: reviewList){
            User user = review.getUserId();
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
    public void updateReview(ReviewUpdateParam reviewUpdateParam) {

        //작성자와 현재 수정하려는 사람이 일치하는지 확인해야 한다
        Long reviewId = reviewUpdateParam.getReviewId(); //현재 작성한 댓글의 번호
        Review review = reviewRepository.findById(reviewId).orElse(null); //댓글 정보 select
        Long reviewUser = review.getUserId().getUserId(); // 댓글 작성자 가져오기
        Long userId = null;
        if(reviewUser==userId){
            review.updateReview(review.getReviewContent()); // 같다면 수정하기
        }
    }

    // 댓글 삭제
    @Override
    public void deleteReview(Long reviewId) {
        //작성자와 현재 삭제하려는 사람이 일치하는지 확인해야 한다
        Optional<Review> review = reviewRepository.findById(reviewId).ofNullable(null);
        Long reviewUser = review.get().getUserId().getUserId();
        Long userId = null;
        if(userId==reviewUser){
            reviewRepository.deleteById(reviewId);
        }

    }
}
