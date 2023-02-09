package com.example.project.diary.controller;

import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.dto.EmotionStatistic;
import com.example.project.diary.model.service.DiaryService;
import com.example.project.recentdiary.model.service.RecentDiaryService;
import com.example.project.user.model.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_HEADER;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DiaryController {
    private final DiaryService diaryService;
    private final RecentDiaryService recentDiaryService;
    private final JwtUtil jwtUtil;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";

    @PostMapping("/diary")
    public ResponseEntity<String> insertDiary(@RequestBody DiaryDto diaryDto, HttpServletRequest request){

        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        diaryDto.setUserId(userId);

        if(diaryService.insertDiary(diaryDto)) {
            recentDiaryService.insertRecentDiary(diaryDto.getUserId(), diaryDto.getDiaryId()); // 최근
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/diary/detail/{diaryId}")
    public ResponseEntity<?> selectOneDiary(@PathVariable Long diaryId, HttpServletRequest request){

        String token = request.getHeader(TOKEN_HEADER);
        Long userId=jwtUtil.getUserId(token);

        DiaryDto diaryDto = diaryService.selectOneDiary(diaryId);

        if(diaryDto == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        recentDiaryService.insertReadDiary(userId, diaryId);
        return new ResponseEntity<>(diaryDto, HttpStatus.OK);
    }

    @GetMapping("/diary/daily/{date}")
    public ResponseEntity<?> selectMyDailyDiaries(@PathVariable("date") String stringDate, HttpServletRequest request) {
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        List<DiaryDto> diaryDtoList = diaryService.selectDailyDiaries(userId, stringDate);
        if(diaryDtoList == null) {
            return new ResponseEntity<>(FAIL,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(diaryDtoList, HttpStatus.OK);
    }

    @GetMapping("/diary/daily/{date}/{userId}")
    public ResponseEntity<?> selectDailyDiaries(@PathVariable Long userId, @PathVariable("date") String stringDate){
        List<DiaryDto> diaryDtoList = diaryService.selectDailyDiaries(userId, stringDate);
        if(diaryDtoList == null) {
            return new ResponseEntity<>(FAIL,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(diaryDtoList, HttpStatus.OK);
    }

    @GetMapping("/diary/count")
    public ResponseEntity<?> selectDiaryCount(HttpServletRequest request) {
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        int diaryCnt = diaryService.selectDiaryCount(userId);
        if(diaryCnt < 0) {
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(diaryCnt, HttpStatus.OK);
    }

    @GetMapping("/diary/statistic/{period}/{date}")
    public ResponseEntity<?> selectMyEmotionStatistic(@PathVariable String period,
                                                    @PathVariable("date") String stringDate,
                                                    HttpServletRequest request){

        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        List<EmotionStatistic> emotionStatisticList = diaryService.selectEmotionStatistic(period, stringDate, userId);
        if(emotionStatisticList == null) {
           return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(emotionStatisticList, HttpStatus.OK);
    }

    @GetMapping("/diary/statistic/{period}/{date}/{userId}")
    public ResponseEntity<?> selectEmotionStatistic(@PathVariable String period,
                                                    @PathVariable("date") String stringDate,
                                                    @PathVariable Long userId){

        List<EmotionStatistic> emotionStatisticList = diaryService.selectEmotionStatistic(period, stringDate, userId);
        if(emotionStatisticList == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(emotionStatisticList, HttpStatus.OK);
    }

    @PutMapping("/diary")
    public ResponseEntity<String> updateDiary(DiaryUpdateParam param, HttpServletRequest request){
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        if(diaryService.updateDiary(userId, param)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/diary/scope/{diaryId}/{scope}")
    public ResponseEntity<String> updateScope(@PathVariable Long diaryId, @PathVariable Integer scope, HttpServletRequest request) {
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        if(diaryService.updateScope(userId, diaryId, scope)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/diary")
    public ResponseEntity<String> deleteDiary(Long diaryId, HttpServletRequest request){
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        if(diaryService.deleteDiary(userId, diaryId)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
    }
}
