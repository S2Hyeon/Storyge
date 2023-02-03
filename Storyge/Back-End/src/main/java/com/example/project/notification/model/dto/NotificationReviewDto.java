package com.example.project.notification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 댓글 알림 insert시 필요한 정보들
public class NotificationReviewDto {

    private Long userId;
    private Long follow; // 현재 로그인 한 사람이 됨
    private Long diaryId;

}