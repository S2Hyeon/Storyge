package com.example.project.follow.controller;

import com.example.project.follow.model.dto.UserNicknameDto;
import com.example.project.follow.model.service.FollowService;
import com.example.project.user.model.dto.UserDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class FollowController {

    private final FollowService followService;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    
    //팔로우 신청하기
    @ApiOperation(value = "팔로우 신청", notes = "팔로우 신청을 보낸다")
    @ApiImplicitParam(name="UserNicknameDto",
                    value = "팔로우 신청하려는 사용자 nickname",
                    required = true,
                    dataType = "String")
    @PostMapping("/follow-wait")
    public ResponseEntity insertFollowWait(@RequestBody UserNicknameDto userNicknameDto){

        if(followService.insertFollowWait(userNicknameDto)){ // 신청 성공
            return new ResponseEntity(SUCCESS, HttpStatus.OK);
        }
        else{
            // 신청 실패
            // -> 신청하려는 상대가 존재하지 않거나 이미 팔로우 신청 중이거나 팔로우 중
            return new ResponseEntity(FAIL, HttpStatus.NO_CONTENT);
        }
    }

    //팔로우 수락
    @ApiOperation(value = "팔로우 수락", notes = "팔로우를 수락한다")
    @ApiImplicitParam(name="UserNicknameDto", value = "팔로우 수락할 사용자의 nickname")
    @PostMapping("/follow")
    public ResponseEntity insertFollower(@RequestBody UserNicknameDto userNicknameDto){

        if(followService.insertFollower(userNicknameDto)){
            return new ResponseEntity(SUCCESS, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(FAIL, HttpStatus.NO_CONTENT);
        }

    }

    //팔로우 수락 대기 목록
    @ApiOperation(value = "팔로우 신청 대기 목록",
            notes = "팔로우 신청을 한 사람들의 목록 조회")
    @GetMapping("/follow-wait")
    public ResponseEntity<List<UserDto>> selectAllFollowWait(){
        List<UserDto> followWaitList = followService.selectAllFollowWait();
        return new ResponseEntity<>(followWaitList, HttpStatus.OK);
    }
    
    
    //내 팔로잉 목록
    @ApiOperation(value = "내 팔로잉 목록",
            notes = "내가 팔로우 하는 사람들의 목록 조회")
    @GetMapping("/following")
    public ResponseEntity<List<UserDto>> selectAllFollowing(){
        List<UserDto> followingList = followService.selectAllFollowing();
        return new ResponseEntity<>(followingList, HttpStatus.OK);
    }

    // 내 팔로워 목록
    @ApiOperation(value = "내 팔로워 목록",
            notes = "나를 팔로우 하는 사람들의 목록 조회")
    @GetMapping("/follower")
    public ResponseEntity<List<UserDto>> selectAllFollower(){
        List<UserDto> followerList = followService.selectAllFollower();
        return new ResponseEntity<>(followerList, HttpStatus.OK);
    }

    //팔로우 대기 삭제
    @ApiOperation(value = "팔로우 대기 삭제",
            notes = "나에게 들어온 팔로우 신청을 삭제한다(거절)")
    @ApiImplicitParam(name="nickname", value = "거절할 사용자의 nickname")
    @DeleteMapping("/follow-wait/{nickname}")
    public ResponseEntity<String> deleteFollowWait(@PathVariable String nickname){
        if(followService.deleteFollowWait(nickname)){
            return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        }
    }

    // 팔로잉 삭제
    @ApiOperation(value = "팔로잉 삭제",
            notes = "팔로우를 취소함 - 내가 팔로우 하고 있는 사람 삭제")
    @ApiImplicitParam(name="nickname", value = "거절할 사용자의 nickname")
    @DeleteMapping("/following/{nickname}")
    public ResponseEntity<String> deleteFollowing(@PathVariable String nickname){
        if(followService.deleteFollowing(nickname)){
            return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(FAIL,HttpStatus.NO_CONTENT);
        }
    }

    // 팔로워 삭제
    @ApiOperation(value = "팔로워 삭제",
            notes = "나를 팔로우 하고 있는 사람 삭제")
    @ApiImplicitParam(name="nickname", value = "삭제할 사용자의 nickname")
    @DeleteMapping("/follower/{nickname}")
    public ResponseEntity<String> deleteFollower(@PathVariable String nickname){
        if(followService.deleteFollower(nickname)){
            return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(FAIL,HttpStatus.NO_CONTENT);
        }
    }








}
