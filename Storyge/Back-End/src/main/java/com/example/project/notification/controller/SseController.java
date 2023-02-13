package com.example.project.notification.controller;

import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_HEADER;
import static com.example.project.user.model.jwt.JwtProperties.TOKEN_PREFIX;

@RestController
@RequiredArgsConstructor
@Api(tags = {"실시간 알림 API"})
public class SseController {

    public static Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();
    private final JwtUtil jwtUtil;


    @ApiOperation(value = "실시간 알림 서버", notes = "토큰 보유시에 /sub 서버를 구독해야 함\n"+
    "SSE 사용\n" + "프론트 측에서는 EventSource 객체 이용해서 메세지 받으면 된다고 함\n"+
    "전달되는 메세지: \n"+
    "연결시 name -> connect\n"+
    "모든 알림의 name -> notification\n" +
    "알림 각각의 data -> 신청: follow wait, 수락: follow accept,댓글:review")
    @GetMapping(value="/sub", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(HttpServletRequest request){
        //현재 로그인한 user 값(pk)
        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);
        System.out.println("");

        // 현재 클라이언트를 위한 SseEmitter 생성
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try{
            sseEmitter.send(SseEmitter.event().name("connect").data("제발")); // 연결
        }catch (IOException e){
            e.printStackTrace();
        }

        // user의 pk 값을 key 값으로 해서 SseEmitter를 저장
        sseEmitters.put(userId, sseEmitter);

        sseEmitter.onCompletion(()->sseEmitters.remove(userId));
        sseEmitter.onTimeout(()->sseEmitters.remove(userId));
        sseEmitter.onError((e)->sseEmitters.remove(userId));

        return ResponseEntity.ok(sseEmitter);


    }

}
