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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

import static com.example.project.notification.controller.SseController.sseEmitters;

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

    
    //userid 알림 받을 사람
    //follow 현재 사용자
    
    @Override
    public Boolean insertFollowWaitNotification(NotificationFollowDto notificationFollowDto) {

        Long notificationUser = notificationFollowDto.getUserId(); // 알림 받을 사람, 즉 팔로우 신청을 받은 사람
        Long followUser = notificationFollowDto.getFollow(); // 팔로우 신청을 한 사람


        notificationRepository.save(Notification.builder()
                .userId(notificationUser)
                .follow(followUser)
                .notiType(WAIT)
                .build());

        if(sseEmitters.containsKey(notificationUser)){
            SseEmitter sseEmitter = sseEmitters.get(notificationUser);
            try {
                sseEmitter.send(SseEmitter.event().reconnectTime(500).name("notification").data("follow wait"));
            }catch (Exception e){
                sseEmitters.remove(notificationUser);
            }
        }

        return true;
    }

    @Override
    public Boolean insertFollowNotification(NotificationFollowDto notificationFollowDto) {

        Long notificationUserId = notificationFollowDto.getUserId(); //알림 받을 사람 id 즉 팔로우 수락이 된 사람
        Long followUserId = notificationFollowDto.getFollow(); // 팔로우를 수락한 사람
        
        notificationRepository.save(Notification.builder()
                .userId(notificationUserId) // 이 사람에게 알림을 보낸다
                .follow(followUserId)
                .notiType(FOLLOW)
                .build());

        if(sseEmitters.containsKey(notificationUserId)){
            SseEmitter sseEmitter = sseEmitters.get(notificationUserId);
            try {
                sseEmitter.send(SseEmitter.event().reconnectTime(500).name("notification").data("follow accept"));
            }catch (Exception e){
                sseEmitters.remove(notificationUserId);
            }
        }

        return true;
    }

    @Override
    public Boolean insertReviewNotification(NotificationReviewDto notificationReviewDto) {

        Long notificationUserId = notificationReviewDto.getUserId(); //알림 받을 사람 id, 다이어리 쓴 사람
        Long followUserId = notificationReviewDto.getFollow(); // 댓글 단 사람
        Long diaryId = notificationReviewDto.getDiaryId();// 댓글이 달린 다이어리 아이디

        notificationRepository.save(Notification.builder()
                .userId(notificationUserId)
                .follow(followUserId)
                .notiType(REVIEW)
                .diaryId(diaryId)
                .build());

        if(sseEmitters.containsKey(notificationUserId)){
            SseEmitter sseEmitter = sseEmitters.get(notificationUserId);
            try {
                sseEmitter.send(SseEmitter.event().reconnectTime(500).name("notification").data("review"));
            }catch (Exception e){
                sseEmitters.remove(notificationUserId);
            }
        }

        return true;
    }

    @Override
    public List<NotificationReponseDto> selectAllNotification(Long userId) {


        List<Notification> notifications = notificationRepository.findTop30ByUserIdOrderByCreatedAtDesc(userId); // 현재 로그인한 user의 알림

        List<NotificationReponseDto> notificationList = new ArrayList<>(); // 알림 목록을 담을 list

        for(Notification noti:notifications){

            User followUser = noti.getFollowUser(); // 알림을 보낸사람 (팔로우를 수락했거나 팔로우를 신청했거나 댓글을 단 사람)
            String type = noti.getNotiType();

            if(type.equals("REVIEW")){ // 댓글일 때
                notificationList.add(NotificationReponseDto.builder()
                        .follow(noti.getFollow())
                        .nickname(followUser.getNickname())
                        .profileImg(followUser.getProfileImg())
                        .notiType(noti.getNotiType())
                        .diaryId(noti.getDiaryId())
                        .build());
            }
            else{ // 팔로우 수락, 신청일 때
                notificationList.add(NotificationReponseDto.builder()
                        .follow(noti.getFollow())
                        .nickname(followUser.getNickname())
                        .profileImg(followUser.getProfileImg())
                        .notiType(noti.getNotiType())
                        .build());
            }


        }

        return notificationList;
    }

}
