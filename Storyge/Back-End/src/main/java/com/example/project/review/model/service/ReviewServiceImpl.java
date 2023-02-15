package com.example.project.review.model.service;

import com.example.project.diary.model.entity.Diary;
import com.example.project.diary.model.repository.DiaryRepository;
import com.example.project.notification.model.dto.NotificationReviewDto;
import com.example.project.notification.model.service.NotificationService;
import com.example.project.review.model.dto.ReviewRequestDto;
import com.example.project.review.model.dto.ReviewResponseDto;
import com.example.project.review.model.dto.ReviewUpdateParam;
import com.example.project.review.model.entity.Review;
import com.example.project.review.model.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final DiaryRepository diaryRepository;

    private final NotificationService notificationService;

    // 댓글 입력
    @Override
    public void insertReview(Long userId, ReviewRequestDto reviewDto) {

        reviewRepository.save(toEntity(reviewDto));

        Long diaryId = reviewDto.getDiaryId();
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();


        // 내가 내 다이어리에 댓글을 달면 알림이 가지 않는다
        if (!Objects.equals(diary.getUserId(), userId)) { // 다른 사람이라면
            // 알림 센터에 추가
            notificationService.insertReviewNotification(NotificationReviewDto.builder()
                    .userId(diary.getUserId()) // 다이어리 작성자에게 알림이 간다
                    .follow(userId)
                    .diaryId(diaryId)
                    .build());
        }

    }

    // 댓글 가져오기
    @Override
    public List<ReviewResponseDto> selectAllReview(Long userId, Long diaryId) {

        // 해당 다이어리에 해당하는 댓글 목록 받아옴
        // 시간 순으로 오름차순 정렬
//        List<ReviewResponseDto> reviewList = reviewRepository.findAllByDiaryIdOrderByCreatedAt(diaryId).stream().map(review -> toDto(review, userId)).collect(Collectors.toList());
//        List<ReviewResponseDto> reviewResponseList = new ArrayList<>();
//        for (Review review : reviewList) {
//
//            User user = review.getUser();
//            Boolean isMe = (userId == user.getUserId()) ? true : false;
//
//            reviewResponseList.add(ReviewResponseDto.builder()
//                    .reviewId(review.getReviewId()) // 댓글 id
//                    .reviewContent(review.getReviewContent()) // 댓글 내용
//                    .userId(user.getUserId()) // 댓글 작성한 사람 id
//                    .isMe(isMe) // 나인지 확인한다
//                    .nickname(user.getNickname()) // 댓글 작성한 사람 닉네임
//                    .profileImg(user.getProfileImg()) // 댓글 작성한 사람 프로필 이미지
//                    .createdAt(review.getCreatedAt()) // 댓글 작성 시간 yy.MM.dd HH:mm
//                    .build());
//        }

        return reviewRepository.findAllByDiaryIdOrderByCreatedAt(diaryId).stream().map(review -> toDto(review, userId)).collect(Collectors.toList());
    }

    // 댓글 수정
    @Override
    public Boolean updateReview(Long userId, ReviewUpdateParam reviewUpdateParam) {

        Review review = reviewRepository.findById(reviewUpdateParam.getReviewId()).orElseThrow();
        if (Objects.equals(userId, review.getUserId())) {
            review.updateReview(reviewUpdateParam.getReviewContent());
            return true;
        }
        //작성자와 현재 수정하려는 사람이 일치하는지 확인해야 한다
//        Long reviewId = reviewUpdateParam.getReviewId(); //현재 작성한 댓글의 번호
//        Optional<Review> review = reviewRepository.findById(reviewId); //댓글 정보 select
//
//        Long reviewUser = review.get().getUserId(); // 댓글 작성자 가져오기
//        if (Objects.equals(reviewUser, userId)) { // 같다면 수정
//            review.get().updateReview(reviewUpdateParam.getReviewContent()); // 같다면 수정하기
//            return true;
//        }
        return false;
    }

    // 댓글 삭제
    @Override
    public Boolean deleteReview(Long userId, Long reviewId) {


        //작성자와 현재 삭제하려는 사람이 일치하는지 확인해야 한다
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        Long reviewUser = review.getUserId(); // 댓글 작성한 사람 아이디

        if (Objects.equals(userId, reviewUser)) { // 같다면 삭제
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;

    }
}
