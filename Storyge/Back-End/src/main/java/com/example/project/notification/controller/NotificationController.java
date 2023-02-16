package com.example.project.notification.controller;

import com.example.project.notification.model.dto.NotificationResponseDto;
import com.example.project.notification.model.dto.NotificationUpdateParam;
import com.example.project.notification.model.service.NotificationService;
import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_HEADER;

@RestController
@RequiredArgsConstructor
@Api(tags = {"알림 API"})
public class NotificationController {

    private static final String SUCCESS = "Success";
    private final NotificationService notificationService;
    private final JwtUtil jwtUtil;

    //알림 목록
    @ApiOperation(value = "알림 목록 조회", notes = "현재 로그인한 사용자의 알림을 30개 가져옴(최근 알림->오래된 알림 순서)\n" +
            "follow: 알림 보낸 사람\n" +
            "notiType: FOLLOW: 팔로우 수락, WAIT: 팔로우 신청, REVIEW: 댓글\n" +
            "isRead- 읽음:1, 안읽음:0")
    @GetMapping("/notification")
    public ResponseEntity<List<NotificationResponseDto>> selectAllNotification(HttpServletRequest request) {
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));

        List<NotificationResponseDto> notificationList = notificationService.selectAllNotification(userId);
        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }


    @ApiOperation(value = "알림 읽음 처리", notes = "notificationId를 담아서 보내면 읽음 처리\n" +
            "isRead-> 1로 보내야 읽음 처리")
    @PutMapping("/notification")
    public ResponseEntity<String> updateNotificationRead(@RequestBody NotificationUpdateParam updateParam) {

        notificationService.updateNotificationRead(updateParam);

        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }


}
