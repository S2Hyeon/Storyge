package com.example.project.notification.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class SseController {

    public static Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();


    @ApiOperation(value = "실시간 알림 서버", notes = "토큰 보유시에만 /sub 서버를 구독해야 함...!")
    @GetMapping("/sub")
    public SseEmitter subscribe(){
        //현재 로그인한 user 값(pk) 가져오는 코드 들어와야 함
        Long userId = (long)2;
        //////////

        // 현재 클라이언트를 위한 SseEmitter 생성
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try{
            sseEmitter.send(SseEmitter.event().name("connect")); // 연결
        }catch (IOException e){
            e.printStackTrace();
        }

        // user의 pk 값을 key 값으로 해서 SseEmitter를 저장
        sseEmitters.put(userId, sseEmitter);

        sseEmitter.onCompletion(()->sseEmitters.remove(userId));
        sseEmitter.onTimeout(()->sseEmitters.remove(userId));
        sseEmitter.onError((e)->sseEmitters.remove(userId));

        return sseEmitter;


    }

}
