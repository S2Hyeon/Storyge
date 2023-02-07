package com.example.project.notification.controller;

import com.example.project.notification.model.dto.NotificationReponseDto;
import com.example.project.notification.model.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";


    //알림 목록
    @ApiOperation(value = "알림 목록 조회", notes = "사용자 알림 목록 리스트 가져옴")
    @GetMapping("/notification")
    public ResponseEntity<List<NotificationReponseDto>> selectAllNotification(){
        List<NotificationReponseDto> notificationList = notificationService.selectAllNotification();
        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }




}
