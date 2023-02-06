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

        // 댓글 입력한 사람이 나라면 알림 안감
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
        // 해당 다이어리에 해당하는 댓글 목록 받아옴
        // 시간 순으로 오름차순 정렬
        List<Review> reviewList = reviewRepository.findByDiaryIdOrderByCreatedAt(diary);
        List<ReviewResponseDto> reviewResponseList = new ArrayList<>();
        for(Review review: reviewList){

            User user = review.getUserId();
            
            reviewResponseList.add(ReviewResponseDto.builder()
                    .reviewId(review.getReviewId()) // 댓글 id
                    .reviewContent(review.getReviewContent()) // 댓글 내용
                    .userId(user.getUserId()) // 댓글 작성한 사람 id
                    .nickname(user.getNickname()) // 댓글 작성한 사람 닉네임
                    .profileImg(user.getProfileImg()) // 댓글 작성한 사람 프로필 이미지
                    .createdAt(review.getCreatedAt()) // 댓글 작성 시간 yy.MM.dd HH:mm
                    .build());
        }

        return reviewResponseList;
    }

    // 댓글 수정
    @Override
    public Boolean updateReview(ReviewUpdateParam reviewUpdateParam) {

        //작성자와 현재 수정하려는 사람이 일치하는지 확인해야 한다
        Long reviewId = reviewUpdateParam.getReviewId(); //현재 작성한 댓글의 번호
        Review review = reviewRepository.findById(reviewId).orElse(null); //댓글 정보 select

        Long reviewUser = review.getUserId().getUserId(); // 댓글 작성자 가져오기
        Long userId = (long)2; // 현재 로그인한 사람 정보
        if(reviewUser==userId){ // 같다면 수정
            review.updateReview(reviewUpdateParam.getReviewContent()); // 같다면 수정하기
            return true;
        }
        return false;
    }

    // 댓글 삭제
    @Override
    public Boolean deleteReview(Long reviewId) {

        //작성자와 현재 삭제하려는 사람이 일치하는지 확인해야 한다
        Review review = reviewRepository.findById(reviewId).orElse(null);
        Long reviewUser = review.getUserId().getUserId(); // 댓글 작성한 사람 아이디
        Long userId = 3L; // 현재 로그인한 사람
        
        if(userId==reviewUser){ // 같다면 삭제
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;

    }
}
