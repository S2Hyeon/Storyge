package com.example.project.notification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// follow 신청, 수락 insert시 필요한 정보들
public class NotificationFollowDto {
    private Long userId;
    private Long follow; // 현재 로그인 한 사람이 된다

}
