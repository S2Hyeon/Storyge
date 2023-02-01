package com.example.project.diary.controller;

import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DiaryController {
    private final DiaryService diaryService;

    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    @PostMapping("/diary")
    public ResponseEntity<String> insertArticle(DiaryDto diaryDto){
        diaryService.insertDiary(diaryDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/diary/detail/{diary_id}")
    public ResponseEntity<DiaryDto> selectOneDiary(@PathVariable("diary_id") Long diaryId){
        return new ResponseEntity<>(diaryService.selectOneDiary(diaryId).orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/diary/{nickname}/{date}")
    public ResponseEntity<List<DiaryDto>> selectDailyDiaries(@PathVariable("nickname") String nickname, @PathVariable Date date){
        return new ResponseEntity<>(diaryService.selectDailyDiaries(nickname, date), HttpStatus.OK);
    }

    @PutMapping("/diary")
    public ResponseEntity<String> updateDiary(DiaryUpdateParam param){
        diaryService.updateDiary(param);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/diary")
    public ResponseEntity<String> deleteDiary(Long diaryId){
        diaryService.deleteDiary(diaryId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
