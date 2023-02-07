package com.example.project.diary.controller;

import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.service.DiaryService;
import com.example.project.recentdiary.model.service.RecentDiaryService;
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
    private final RecentDiaryService recentDiaryService;

    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    @PostMapping("/diary")
    public ResponseEntity<String> insertDiary(@RequestBody DiaryDto diaryDto){
        if(diaryService.insertDiary(diaryDto)) {
            recentDiaryService.insertRecentDiary(diaryDto.getUserId(), diaryDto.getDiaryId()); // 최근
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/diary/detail/{diary_id}")
    public ResponseEntity<?> selectOneDiary(@PathVariable("diary_id") Long diaryId){
        DiaryDto diaryDto = diaryService.selectOneDiary(diaryId).orElse(null);
        if(diaryDto == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        }
        recentDiaryService.insertReadDiary(diaryId);
        return new ResponseEntity<>(diaryDto, HttpStatus.OK);
    }

    @GetMapping("/diary/{nickname}/{date}")
    public ResponseEntity<List<DiaryDto>> selectDailyDiaries(@PathVariable("nickname") String nickname, @PathVariable("date") String stringDate){
        return new ResponseEntity<>(diaryService.selectDailyDiaries(nickname, stringDate), HttpStatus.OK);
    }

    @PutMapping("/diary")
    public ResponseEntity<String> updateDiary(DiaryUpdateParam param){
        if(diaryService.updateDiary(param)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/diary")
    public ResponseEntity<String> deleteDiary(Long diaryId){
        if(diaryService.deleteDiary(diaryId)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }
}
