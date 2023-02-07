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

        User user = userRepository.findById(notificationFollowDto.getUserId()).orElse(null); // 현재 user
        User follow = userRepository.findById(notificationFollowDto.getFollow()).orElse(null);

        if(user==null || follow==null){
            return false;
        }

        notificationRepository.save(Notification.builder()
                .userId(user)
                .follow(follow)
                .notiType(WAIT)
                .build());

        Long notificationUser = user.getUserId(); //알림 받을 사람 id
        if(sseEmitters.containsKey(notificationUser)){
            SseEmitter sseEmitter = sseEmitters.get(notificationUser);
            try {
                sseEmitter.send(SseEmitter.event().name("notification").data("follow wait"));
            }catch (Exception e){
                sseEmitters.remove(notificationUser);
            }
        }

        return true;
    }

    @Override
    public Boolean insertFollowNotification(NotificationFollowDto notificationFollowDto) {

//        if(userId!=notificationFollowDto.getFollow()){
//            return false;
//        }

        User user = userRepository.findById(notificationFollowDto.getUserId()).orElse(null);
        User follow = userRepository.findById(notificationFollowDto.getFollow()).orElse(null);

        if(user==null || follow==null){
            return false;
        }
//        User user = notificationFollowDto.getUserId();
//        User follow = notificationFollowDto.getFollow();
        notificationRepository.save(Notification.builder()
                .userId(user)
                .follow(follow)
                .notiType(FOLLOW)
                .build());

        Long notificationUserId = user.getUserId(); //알림 받을 사람 id
        if(sseEmitters.containsKey(notificationUserId)){
            SseEmitter sseEmitter = sseEmitters.get(notificationUserId);
            try {
                sseEmitter.send(SseEmitter.event().name("notification").data("follow accept"));
            }catch (Exception e){
                sseEmitters.remove(notificationUserId);
            }
        }

        return true;
    }

    @Override
    public Boolean insertReviewNotification(NotificationReviewDto notificationReviewDto) {

        User user = userRepository.findById(notificationReviewDto.getUserId()).orElse(null);
        User follow = userRepository.findById(notificationReviewDto.getFollow()).orElse(null);
        Diary diary = diaryRepository.findById(notificationReviewDto.getDiaryId()).orElse(null);

        notificationRepository.save(Notification.builder()
                .userId(user)
                .follow(follow)
                .notiType(REVIEW)
                .diaryId(diary)
                .build());

        Long notificationUserId = user.getUserId(); //알림 받을 사람 id
        if(sseEmitters.containsKey(notificationUserId)){
            SseEmitter sseEmitter = sseEmitters.get(notificationUserId);
            try {
                sseEmitter.send(SseEmitter.event().name("notification").data("review"));
            }catch (Exception e){
                sseEmitters.remove(notificationUserId);
            }
        }

        return true;
    }

    @Override
    public List<NotificationReponseDto> selectAllNotification(Long userId) {

        User currentUser = userRepository.findById(userId).orElse(null); // 현재 user 가져오기

        List<Notification> notifications = notificationRepository.findTop30ByUserIdOrderByCreatedAtDesc(currentUser);
        List<NotificationReponseDto> notificationList = new ArrayList<>();
        for(Notification noti:notifications){

            User user = userRepository.findById(noti.getUserId().getUserId()).orElse(null);
            String type = noti.getNotiType();
            if(type.equals("REVIEW")){
                notificationList.add(NotificationReponseDto.builder()
                        .follow(noti.getFollow().getUserId())
                        .nickname(user.getNickname())
                        .profileImg(user.getProfileImg())
                        .notiType(noti.getNotiType())
                        .diaryId(noti.getDiaryId().getDiaryId())
                        .build());
            }
            else{
                notificationList.add(NotificationReponseDto.builder()
                        .follow(noti.getFollow().getUserId())
                        .nickname(user.getNickname())
                        .profileImg(user.getProfileImg())
                        .notiType(noti.getNotiType())
                        .build());
            }


        }

        return notificationList;
    }

}
