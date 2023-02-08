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
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/diary/detail/{diary_id}")
    public ResponseEntity<?> selectOneDiary(@PathVariable("diary_id") Long diaryId){
        DiaryDto diaryDto = diaryService.selectOneDiary(diaryId);
        if(diaryDto == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(diaryDto, HttpStatus.OK);
    }

    @GetMapping("/diary/{nickname}/{date}")
    public ResponseEntity<?> selectDailyDiaries(@PathVariable("nickname") String nickname, @PathVariable("date") String stringDate){
        List<DiaryDto> diaryDtoList = diaryService.selectDailyDiaries(nickname, stringDate);
        if(diaryDtoList == null) {
            return new ResponseEntity<>(FAIL,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(diaryDtoList, HttpStatus.OK);
    }

    @GetMapping("/diary/count/{userId}")
    public ResponseEntity<?> selectDiaryCount(@PathVariable Long userId){
        int diaryCnt = diaryService.selectDiaryCount(userId);
        if(diaryCnt < 0) {
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(diaryCnt, HttpStatus.OK);
    }

    @PutMapping("/diary")
    public ResponseEntity<String> updateDiary(DiaryUpdateParam param){
        if(diaryService.updateDiary(param)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/diary/{diaryId}/{scope}")
    public ResponseEntity<String> updateScope(@PathVariable long diaryId, @PathVariable int scope) {
        if(diaryService.updateScope(diaryId, scope)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/diary")
    public ResponseEntity<String> deleteDiary(Long diaryId){
        if(diaryService.deleteDiary(diaryId)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
    }
}
