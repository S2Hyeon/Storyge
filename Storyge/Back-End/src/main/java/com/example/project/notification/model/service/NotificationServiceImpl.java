package com.example.project.notification.model.service;

import com.example.project.diary.model.entity.Diary;
import com.example.project.diary.model.repository.DiaryRepository;
import com.example.project.notification.model.dto.NotificationFollowDto;
import com.example.project.notification.model.dto.NotificationReponseDto;
import com.example.project.notification.model.dto.NotificationReviewDto;
import com.example.project.notification.model.entity.Notification;
import com.example.project.notification.model.repository.NotificationRepository;
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
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

    private final String WAIT = "WAIT"; // 팔로우 대기
    private final String FOLLOW = "FOLLOW"; // 팔로우 수락
    private final String REVIEW = "REVIEW"; // 댓글

    @Override
    public void insertFollowWaitNotification(NotificationFollowDto notificationFollowDto) {
        User user = userRepository.findById(notificationFollowDto.getUserId()).orElse(null);
        User follow = userRepository.findById(notificationFollowDto.getFollow()).orElse(null);
//        User user = notificationFollowDto.getUserId();
//        User follow = notificationFollowDto.getFollow();
        notificationRepository.save(Notification.builder()
                .userId(user)
                .follow(follow)
                .notiType(WAIT)
                .build());
    }

    @Override
    public void insertFollowNotification(NotificationFollowDto notificationFollowDto) {
        User user = userRepository.findById(notificationFollowDto.getUserId()).orElse(null);
        User follow = userRepository.findById(notificationFollowDto.getFollow()).orElse(null);
//        User user = notificationFollowDto.getUserId();
//        User follow = notificationFollowDto.getFollow();
        notificationRepository.save(Notification.builder()
                .userId(user)
                .follow(follow)
                .notiType(FOLLOW)
                .build());
    }

    @Override
    public void insertReviewNotificatino(NotificationReviewDto notificationReviewDto) {
        User user = userRepository.findById(notificationReviewDto.getUserId()).orElse(null);
        User follow = userRepository.findById(notificationReviewDto.getFollow()).orElse(null);
        Diary diary = diaryRepository.findById(notificationReviewDto.getDiaryId()).orElse(null);

        notificationRepository.save(Notification.builder()
                .userId(user)
                .follow(follow)
                .notiType(REVIEW)
                .diaryId(diary)
                .build());
    }

    @Override
    public List<NotificationReponseDto> selectAllNotification() {

        User currentUser = null; // 현재 user 가져오기

        List<Notification> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(currentUser);
        List<NotificationReponseDto> notificationList = new ArrayList<>();
        for(Notification noti:notifications){
            notificationList.add(NotificationReponseDto.builder()
                    .follow(noti.getFollow().getUserId())
                    .notiType(noti.getNotiType())
                    .diaryId(noti.getDiaryId().getDiaryId())
                    .build());
        }

        return notificationList;
    }
}
