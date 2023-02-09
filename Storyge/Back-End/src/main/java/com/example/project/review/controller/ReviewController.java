package com.example.project.review.controller;

import com.example.project.review.model.dto.ReviewRequsetDto;
import com.example.project.review.model.dto.ReviewResponseDto;
import com.example.project.review.model.dto.ReviewUpdateParam;
import com.example.project.review.model.service.ReviewService;
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
@Api(tags = {"다이어리의 댓글 API"})
public class ReviewController {

    private final ReviewService reviewService;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    private final JwtUtil jwtUtil;

    // 댓글 입력
    @ApiOperation(value = "댓글 입력", notes = "다이어리에 댓글 달기")
    @PostMapping("/review")
    public ResponseEntity<String> insertReview(HttpServletRequest request, @RequestBody @ApiParam(value = "댓글 입력시 필요한 정보") ReviewRequsetDto reviewRequsetDto){

        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);

        reviewService.insertReview(userId, reviewRequsetDto);
        return new ResponseEntity(SUCCESS, HttpStatus.OK);
    }

    // 다이어리에 해당하는 댓글 조회
    @ApiOperation(value = "댓글 목록 조회", notes = "diaryId를 통해 그 diary에 해당하는 댓글 목록을 조회한다")
    @GetMapping("/review/{diaryId}")
    public ResponseEntity<List<ReviewResponseDto>> selectAllReview(@PathVariable @ApiParam(value = "댓글 조회할 다이어리의 id(pk)") Long diaryId){
        List<ReviewResponseDto> reviewList = reviewService.selectAllReview(diaryId);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    // 댓글 수정
    @ApiOperation(value = "댓글 수정", notes = "댓글 수정, 수정할 내용과 그 댓글의 id 필요")
    @PutMapping("/review")
    public ResponseEntity<String> updateReview(HttpServletRequest request, @RequestBody @ApiParam(value = "댓글 수정시 필요한 정보") ReviewUpdateParam reviewUpdateParam){

        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);

        if(reviewService.updateReview(userId, reviewUpdateParam)){
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        }
    }

    // 댓글 삭제
    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제")
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReview(HttpServletRequest request, @PathVariable @ApiParam(value = "삭제할 댓글의 id(pk)") Long reviewId){

        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);

        if(reviewService.deleteReview(userId, reviewId)) {
           return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
       }
    }
}
