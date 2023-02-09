package com.example.project.daily_emotion.controller;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.daily_emotion.model.service.DailyEmotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.example.project.user.model.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_HEADER;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = {"캘린더 API"})
public class DailyEmotionController {
    private final DailyEmotionService dailyEmotionService;
    private final JwtUtil jwtUtil;

    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";

    @ApiOperation(value = "한달 감정 가져오기", notes = "조회하려는 달의 일별 감정을 가져온다 (날짜: 감정)")
    @GetMapping("/daily/{date}")
    public ResponseEntity<?> selectMyDailyEmotion(@PathVariable("date") String stringDate, HttpServletRequest request){
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        List<DailyEmotionDto> dailyEmotionDtoList = dailyEmotionService.selectDailyEmotions(userId, stringDate);
        if(dailyEmotionDtoList == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dailyEmotionDtoList, HttpStatus.OK);
    }

    @GetMapping("/daily/{date}/{userId}")
    public ResponseEntity<?> selectDailyEmotion(@PathVariable("date") String stringDate, @PathVariable Long userId){
        List<DailyEmotionDto> dailyEmotionDtoList = dailyEmotionService.selectDailyEmotions(userId, stringDate);
        if(dailyEmotionDtoList == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dailyEmotionDtoList, HttpStatus.OK);
    }

}
