package com.example.project.daily_emotion.controller;

import com.example.project.daily_emotion.model.service.DailyEmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DailyEmotionController {
    private final DailyEmotionService dailyEmotionService;

    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";

    @GetMapping("/daily/{nickname}/{date}")
    public ResponseEntity<Map<Integer, String>> selectDailyEmotion(@PathVariable("nickname") String nickname, @PathVariable("date") String stringDate){
        return new ResponseEntity<>(dailyEmotionService.selectDailyEmotions(nickname, stringDate), HttpStatus.OK);
    }

//    // N + 1문제??
//    @GetMapping("/daily/{nickname}/{date}")
//    public ResponseEntity<Long> selectDailyEmotion(@PathVariable("nickname") String nickname, @PathVariable("date") String stringDate){
//        return new ResponseEntity<>(dailyEmotionService.selectDailyEmotionCount(nickname, stringDate), HttpStatus.OK);
//    }
}
