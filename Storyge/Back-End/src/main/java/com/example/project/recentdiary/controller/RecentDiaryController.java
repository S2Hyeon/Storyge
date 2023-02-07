package com.example.project.recentdiary.controller;

import com.example.project.recentdiary.model.dto.RecentDiaryResponseDto;
import com.example.project.recentdiary.model.service.RecentDiaryService;
import com.example.project.user.model.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_PREFIX;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class RecentDiaryController {

    private final RecentDiaryService recentDiaryService;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    private final JwtUtil jwtUtil;

    @GetMapping("/recent")
    public ResponseEntity<List<RecentDiaryResponseDto>> selectAllRecentDiary(@RequestHeader(name = TOKEN_PREFIX) String token){

        Long userId = jwtUtil.getUserId(token);

        List<RecentDiaryResponseDto> recentDiaryList = recentDiaryService.selectAllRecentDiary(userId);
        return new ResponseEntity<>(recentDiaryList, HttpStatus.OK);
    }



}
