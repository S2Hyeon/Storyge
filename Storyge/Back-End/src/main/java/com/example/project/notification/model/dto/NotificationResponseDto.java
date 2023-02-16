package com.example.project.notification.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "알림", description = "알림 목록 요청시 반환하는 정보")
// 알림 리스트 반환시 필요한 dto
public class NotificationResponseDto {

    @ApiModelProperty(value = "notification id", example = "0")
    private Long notificationId;
    @ApiModelProperty(value = "팔로우 신청/수락을 했거나, 댓글 단 사용자 아이디(pk)", example = "0")
    private Long follow;
    @ApiModelProperty(value = "팔로우 신청/수락을 했거나, 댓글 단 사용자 닉네임")
    private String nickname;
    @ApiModelProperty(value = "팔로우 신청/수락을 했거나, 댓글 단 사용자 프로필 url")
    private String profileImg;
    @ApiModelProperty(value = "팔로우 신청(WAIT)/수락(FOLLOW), 댓글(REVIEW) 구분")
    private String notiType;
    @ApiModelProperty(value = "댓글이 달렸을 때만 그 다이어리의 diaryId 반환", example = "0")
    private Long diaryId;
    @ApiModelProperty(value = "읽으면 1 안읽었으면 0", example = "0")
    private int isRead;

}
