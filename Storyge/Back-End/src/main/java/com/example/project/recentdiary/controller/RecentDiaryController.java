package com.example.project.recentdiary.controller;

import com.example.project.recentdiary.model.dto.RecentDiaryResponseDto;
import com.example.project.recentdiary.model.service.RecentDiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class RecentDiaryController {

    private final RecentDiaryService recentDiaryService;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";

    @GetMapping("/recent")
    public ResponseEntity<List<RecentDiaryResponseDto>> selectAllRecentDiary(){
        List<RecentDiaryResponseDto> recentDiaryList = recentDiaryService.selectAllRecentDiary();
        return new ResponseEntity<>(recentDiaryList, HttpStatus.OK);
    }



}
