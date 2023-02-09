package com.example.project.diary.controller;

import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.dto.EmotionStatistic;
import com.example.project.diary.model.service.DiaryService;
import com.example.project.recentdiary.model.service.RecentDiaryService;
import com.example.project.user.model.jwt.JwtProperties;
import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = {"다이어리 API"})
public class DiaryController {
    private final DiaryService diaryService;
    private final RecentDiaryService recentDiaryService;
    private final JwtUtil jwtUtil;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";


    @ApiOperation(value = "일기 작성", notes = "일기 작성하기")
    @PostMapping("/diary")
    public ResponseEntity<String> insertDiary(@RequestBody DiaryDto diaryDto){
        if(diaryService.insertDiary(diaryDto)) {
            recentDiaryService.insertRecentDiary(diaryDto.getUserId(), diaryDto.getDiaryId()); // 최근
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "일기 읽기", notes = "일기 1개를 선택한다")
    @GetMapping("/diary/detail/{diary_id}")
    public ResponseEntity<?> selectOneDiary(HttpServletRequest request, @PathVariable("diary_id") Long diaryId){

        String token = request.getHeader(TOKEN_HEADER);
        Long userId=jwtUtil.getUserId(token);

        DiaryDto diaryDto = diaryService.selectOneDiary(diaryId);

        if(diaryDto == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        recentDiaryService.insertReadDiary(userId, diaryId);
        return new ResponseEntity<>(diaryDto, HttpStatus.OK);
    }

    @ApiOperation(value = "일별 일기 목록", notes = "선택한 날짜의 일기들을 가져온다")
    @GetMapping("/diary/{nickname}/{date}")
    public ResponseEntity<?> selectDailyDiaries(@PathVariable("nickname") String nickname, @PathVariable("date") String stringDate){
        List<DiaryDto> diaryDtoList = diaryService.selectDailyDiaries(nickname, stringDate);
        if(diaryDtoList == null) {
            return new ResponseEntity<>(FAIL,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(diaryDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "일기 개수", notes = "오늘 하루동안 작성한 일기 개수 반환")
    @GetMapping("/diary/count/{userId}")
    public ResponseEntity<?> selectDiaryCount(@PathVariable Long userId){
        int diaryCnt = diaryService.selectDiaryCount(userId);
        if(diaryCnt < 0) {
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(diaryCnt, HttpStatus.OK);
    }

    @GetMapping("/diary/{period}/{date}")
    public ResponseEntity<?> selectMyEmotionStatistic(@PathVariable String period,
                                                    @PathVariable("date") String stringDate,
                                                    HttpServletRequest request){

        long userId = jwtUtil.getUserId(request.getHeader(JwtProperties.TOKEN_HEADER));
        List<EmotionStatistic> emotionStatisticList = diaryService.selectEmotionStatistic(period, stringDate, userId);
        if(emotionStatisticList == null) {
           return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(emotionStatisticList, HttpStatus.OK);
    }

    @GetMapping("/diary/{period}/{date}/{userId}")
    public ResponseEntity<?> selectEmotionStatistic(@PathVariable String period,
                                                    @PathVariable("date") String stringDate,
                                                    @PathVariable Long userId){

        List<EmotionStatistic> emotionStatisticList = diaryService.selectEmotionStatistic(period, stringDate, userId);
        if(emotionStatisticList == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(emotionStatisticList, HttpStatus.OK);
    }

    @ApiOperation(value = "일기 수정", notes = "일기를 수정한다")
    @PutMapping("/diary")
    public ResponseEntity<String> updateDiary(DiaryUpdateParam param){
        if(diaryService.updateDiary(param)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "일기 공개 여부 변경", notes = "일기 공개 여부를 변경한다 - 1: 공개, 0: 비공개 ")
    @PutMapping("/diary/{diaryId}/{scope}")
    public ResponseEntity<String> updateScope(@PathVariable @ApiParam(value = "공개 여부를 변경할 일기 id(pk)") long diaryId, @PathVariable @ApiParam(value = "공개 여부(1: 공개, 0:비공개)") int scope) {
        if(diaryService.updateScope(diaryId, scope)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "일기 삭제", notes = "선택한 일기를 삭제한다")
    @DeleteMapping("/diary")
    public ResponseEntity<String> deleteDiary(@ApiParam(value = "삭제할 일기 Id(pk)")Long diaryId){
        if(diaryService.deleteDiary(diaryId)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
    }
}
