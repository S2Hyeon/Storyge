package com.example.project.diary.controller;

import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DiaryController {
    private final DiaryService diaryService;

    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    @PostMapping("/diary")
    public ResponseEntity<String> insertDiary(@RequestBody DiaryDto diaryDto){
        if(diaryService.insertDiary(diaryDto)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/diary/detail/{diary_id}")
    public ResponseEntity<?> selectOneDiary(@PathVariable("diary_id") Long diaryId){
        DiaryDto diaryDto = diaryService.selectOneDiary(diaryId).orElse(null);
        if(diaryDto == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diaryDto, HttpStatus.OK);
    }

    @GetMapping("/diary/{nickname}/{date}")
    public ResponseEntity<List<DiaryDto>> selectDailyDiaries(@PathVariable("nickname") String nickname, @PathVariable("date") String stringDate){
        return new ResponseEntity<>(diaryService.selectDailyDiaries(nickname, stringDate), HttpStatus.OK);
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
