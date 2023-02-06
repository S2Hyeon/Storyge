//package com.example.project.review.controller;
//
//import com.example.project.review.model.dto.ReviewRequsetDto;
//import com.example.project.review.model.dto.ReviewResponseDto;
//import com.example.project.review.model.dto.ReviewUpdateParam;
//import com.example.project.review.model.service.ReviewService;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin
//public class ReviewController {
//
//    private final ReviewService reviewService;
//    private static final String SUCCESS = "Success";
//    private static final String FAIL = "Fail";
//
//    // 댓글 입력
//    @ApiOperation(value = "댓글 입력", notes = "다이어리에 댓글 달기")
//    @PostMapping("/review")
//    public ResponseEntity<String> insertReview(@RequestBody ReviewRequsetDto reviewRequsetDto){
//        reviewService.insertReview(reviewRequsetDto);
//        return new ResponseEntity(SUCCESS, HttpStatus.OK);
//    }
//
//    // 다이어리에 해당하는 댓글 조회
//    @ApiOperation(value = "댓글 목록 조회", notes = "diaryId를 통해 그 diary에 해당하는 댓글 목록을 조회한다")
//    @GetMapping("/review/{diaryId}")
//    public ResponseEntity<List<ReviewResponseDto>> selectAllReview(@PathVariable Long diaryId){
//        List<ReviewResponseDto> reviewList = reviewService.selectAllReview(diaryId);
//        return new ResponseEntity<>(reviewList, HttpStatus.OK);
//    }
//
//    // 댓글 수정
//    @ApiOperation(value = "댓글 수정", notes = "댓글 수정, 수정할 내용과 그 댓글의 id 필요")
//    @PutMapping("/review")
//    public ResponseEntity<String> updateReview(@RequestBody ReviewUpdateParam reviewUpdateParam){
//
//        if(reviewService.updateReview(reviewUpdateParam)){
//            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
//        }
//    }
//
//    // 댓글 삭제
//    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제")
//    @DeleteMapping("/review/{reviewId}")
//    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
//       if(reviewService.deleteReview(reviewId)) {
//           return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
//       }
//       else{
//           return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
//       }
//    }
//}
