package com.example.project.recentdiary.controller;

import com.example.project.recentdiary.model.dto.RecentDiaryResponseDto;
import com.example.project.recentdiary.model.service.RecentDiaryService;
import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.project.user.model.jwt.JwtProperties.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = {"Following의 최근 일기(스토리) API"})
public class RecentDiaryController {

    private final RecentDiaryService recentDiaryService;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    private final JwtUtil jwtUtil;

    @GetMapping("/recent")
    public ResponseEntity<List<RecentDiaryResponseDto>> selectAllRecentDiary(HttpServletRequest request){
        System.out.println("request: "+request.getHeader(TOKEN_PREFIX));
        System.out.println("request: "+request.getHeader(ACCESS_TOKEN));


        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);

        List<RecentDiaryResponseDto> recentDiaryList = recentDiaryService.selectAllRecentDiary(userId);
        return new ResponseEntity<>(recentDiaryList, HttpStatus.OK);
    }


}
