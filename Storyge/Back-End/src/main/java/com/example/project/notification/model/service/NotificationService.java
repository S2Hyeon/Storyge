package com.example.project.notification.model.service;

import com.example.project.notification.model.dto.NotificationFollowDto;
import com.example.project.notification.model.dto.NotificationResponseDto;
import com.example.project.notification.model.dto.NotificationReviewDto;
import com.example.project.notification.model.dto.NotificationUpdateParam;

import java.util.List;

public interface NotificationService {

    // 팔로우 신청 알림
    Boolean insertFollowWaitNotification(NotificationFollowDto notificationFollowDto);

    // 팔로우 수락 알림
    Boolean insertFollowNotification(NotificationFollowDto notificationFollowDto);

    // 댓글 알림
    Boolean insertReviewNotification(NotificationReviewDto notificationReviewDto);

    // 알림 목록 가져오기
    List<NotificationResponseDto> selectAllNotification(Long userId);

    //읽음
    void updateNotificationRead(NotificationUpdateParam updateParam);

}
