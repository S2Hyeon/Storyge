package com.example.project.daily_emotion.controller;

import com.example.project.daily_emotion.model.dto.DailyEmotionDto;
import com.example.project.daily_emotion.model.service.DailyEmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DailyEmotionController {
    private final DailyEmotionService dailyEmotionService;

    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    @PostMapping("/diary")
    public ResponseEntity<String> insertArticle(DailyEmotionDto dailyEmotionDto){
        dailyEmotionService.insertDiary(dailyEmotionDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/diary/detail/{diary_id}")
    public ResponseEntity<DailyEmotionDto> selectOneDiary(@PathVariable("diary_id") Long diaryId){
        return new ResponseEntity<>(dailyEmotionService.selectOneDiary(diaryId).orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/diary/{nickname}/{date}")
    public ResponseEntity<List<DailyEmotionDto>> selectDailyDiaries(@PathVariable("nickname") String nickname, @PathVariable Date date){
        return new ResponseEntity<>(dailyEmotionService.selectDailyDiaries(nickname, date), HttpStatus.OK);
    }

    @PutMapping("/diary")
    public ResponseEntity<String> updateDiary(DiaryUpdateParam param){
        dailyEmotionService.updateDiary(param);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/diary")
    public ResponseEntity<String> deleteDiary(Long diaryId){
        dailyEmotionService.deleteDiary(diaryId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
