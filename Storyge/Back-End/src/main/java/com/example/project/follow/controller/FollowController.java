package com.example.project.follow.controller;

import com.example.project.follow.model.dto.UserIdDto;
import com.example.project.follow.model.service.FollowService;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_HEADER;
import static com.example.project.user.model.jwt.JwtProperties.TOKEN_PREFIX;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = {"팔로워, 팔로잉 관련 API"})
public class FollowController {

    private final FollowService followService;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    private final JwtUtil jwtUtil;

    //팔로우 신청하기
    @ApiOperation(value = "팔로우 신청", notes = "상대방에게 팔로우 신청을 보낸다")
    @PostMapping("/follow-wait")
    public ResponseEntity insertFollowWait(HttpServletRequest request, @RequestBody @ApiParam(value = "신청할 사용자의 userId(pk)")  UserIdDto userIdDto){

        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);

        if(followService.insertFollowWait(userId, userIdDto)){ // 신청 성공
            return new ResponseEntity(SUCCESS, HttpStatus.OK);
        }
        else{
            // 신청 실패
            // -> 신청하려는 상대가 존재하지 않거나 이미 팔로우 신청 중이거나 팔로우 중
            return new ResponseEntity(FAIL, HttpStatus.NO_CONTENT);
        }
    }

    //팔로우 수락
    @ApiOperation(value = "팔로우 수락", notes = "나에게 들어온 팔로우를 수락한다")
    @PostMapping("/follow")
    public ResponseEntity insertFollower(HttpServletRequest request, @RequestBody @ApiParam(value = "수락할 사용자의 userId(pk)") UserIdDto userIdDto){

        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);

        if(followService.insertFollower(userId, userIdDto)){
            return new ResponseEntity(SUCCESS, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(FAIL, HttpStatus.NO_CONTENT);
        }

    }

    //팔로우 수락 대기 목록
    @ApiOperation(value = "팔로우 신청 대기 목록",
            notes = "나에게 팔로우 신청을 한 사람들의 목록 조회")
    @GetMapping("/follow-wait")
    public ResponseEntity<List<UserDto>> selectAllFollowWait(HttpServletRequest request){

        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);

        List<UserDto> followWaitList = followService.selectAllFollowWait(userId);
        return new ResponseEntity<>(followWaitList, HttpStatus.OK);
    }


    //내 팔로잉 목록
    @ApiOperation(value = "내 팔로잉 목록",
            notes = "내가 팔로우 하는 사람들의 목록 조회")
    @GetMapping("/following")
    public ResponseEntity<List<UserDto>> selectAllFollowing(HttpServletRequest request){

        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);

        List<UserDto> followingList = followService.selectAllFollowing(userId);
        return new ResponseEntity<>(followingList, HttpStatus.OK);
    }

    // 내 팔로워 목록
    @ApiOperation(value = "내 팔로워 목록",
            notes = "나를 팔로우 하는 사람들의 목록 조회")
    @GetMapping("/follower")
    public ResponseEntity<List<UserDto>> selectAllFollower(HttpServletRequest request){

        Enumeration<String> em =request.getHeaderNames();
        System.out.println("---------");
        while(em.hasMoreElements()){
            String name = em.nextElement() ;
            String val = request.getHeader(name) ;

            System.out.println(name + "-" + val) ;
        }
        System.out.println("--------");

        String token = request.getHeader(TOKEN_HEADER);
        Long userId = jwtUtil.getUserId(token);

        List<UserDto> followerList = followService.selectAllFollower(userId);
        return new ResponseEntity<>(followerList, HttpStatus.OK);
    }

    //팔로우 대기 삭제
    @ApiOperation(value = "팔로우 대기 삭제",
            notes = "나에게 들어온 팔로우 신청을 삭제한다(거절)")
    @ApiImplicitParam(name="userId", value = "거절할 사용자의 userId(pk)", example = "0")
    @DeleteMapping("/follow-wait/{userId}")
    public ResponseEntity<String> deleteFollowWait(HttpServletRequest request, @PathVariable Long userId){

        String token = request.getHeader(TOKEN_HEADER);
        Long currentUser = jwtUtil.getUserId(token);

        if(followService.deleteFollowWait(currentUser, userId)){
            return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        }
    }

    // 팔로잉 삭제
    @ApiOperation(value = "팔로잉 삭제",
            notes = "팔로우를 취소함 - 내가 팔로우 하고 있는 사람 삭제")
    @ApiImplicitParam(name="userId", value = "삭제할 사용자(팔로잉)의 userId(pk)", example = "0")
    @DeleteMapping("/following/{userId}")
    public ResponseEntity<String> deleteFollowing(HttpServletRequest request, @PathVariable Long userId){

        String token = request.getHeader(TOKEN_HEADER);
        Long currentUser = jwtUtil.getUserId(token);

        if(followService.deleteFollowing(currentUser, userId)){
            return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(FAIL,HttpStatus.NO_CONTENT);
        }
    }

    // 팔로워 삭제
    @ApiOperation(value = "팔로워 삭제",
            notes = "나를 팔로우 하고 있는 사람 삭제")
    @ApiImplicitParam(name="userId", value = "삭제할 사용자(팔로워)의 userId(pk)", example = "0")
    @DeleteMapping("/follower/{userId}")
    public ResponseEntity<String> deleteFollower(HttpServletRequest request, @PathVariable Long userId){

        String token = request.getHeader(TOKEN_HEADER);
        Long currentUser = jwtUtil.getUserId(token);


        if(followService.deleteFollower(currentUser, userId)){
            return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(FAIL,HttpStatus.NO_CONTENT);
        }
    }








}
