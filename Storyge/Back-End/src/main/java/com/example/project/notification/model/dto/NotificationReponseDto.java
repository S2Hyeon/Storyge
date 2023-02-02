package com.example.project.notification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 알림 리스트 반환시 필요한 dto
public class NotificationReponseDto {

    private Long follow;
    private String notiType;
    private Long diaryId;

}
