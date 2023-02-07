package com.example.project.notification.controller;

import com.example.project.notification.model.dto.NotificationReponseDto;
import com.example.project.notification.model.service.NotificationService;
import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_PREFIX;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = {"알림 API"})
public class NotificationController {

    private final NotificationService notificationService;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";

    private final JwtUtil jwtUtil;

    //알림 목록
    @ApiOperation(value = "알림 목록 조회", notes = "사용자 알림 목록 리스트 가져옴")
    @GetMapping("/notification")
    public ResponseEntity<List<NotificationReponseDto>> selectAllNotification(@RequestHeader(name = TOKEN_PREFIX) String token){

        Long userId = jwtUtil.getUserId(token);

        List<NotificationReponseDto> notificationList = notificationService.selectAllNotification(userId);
        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }




}
