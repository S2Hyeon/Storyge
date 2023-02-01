package com.example.project.follow.controller;

import com.example.project.follow.model.dto.UserNicknameDto;
import com.example.project.follow.model.service.FollowService;
import com.example.project.user.model.dto.UserDto;
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
    @PostMapping("/follow-wait")
    public ResponseEntity insertFollowWait(@RequestBody UserNicknameDto following){
        System.out.println("실행");
        followService.insertFollowWait(following);
        return new ResponseEntity(SUCCESS, HttpStatus.OK);
    }

    //팔로우 수락
    @PostMapping("/follow")
    public ResponseEntity insertFollower(@RequestBody UserNicknameDto follower){

        followService.insertFollower(follower);

        return new ResponseEntity(SUCCESS, HttpStatus.OK);
    }

    //팔로우 수락 대기 목록
    @GetMapping("/follow-wait")
    public ResponseEntity<List<UserDto>> selectAllFollowWait(){
        List<UserDto> followWaitList = followService.selectAllFollowWait();
        return new ResponseEntity<>(followWaitList, HttpStatus.OK);
    }
    
    
    //내 팔로잉 목록
    @GetMapping("/following")
    public ResponseEntity<List<UserDto>> selectAllFollowing(){
        List<UserDto> followingList = followService.selectAllFollowing();
        return new ResponseEntity<>(followingList, HttpStatus.OK);
    }

    // 내 팔로워 목록
    @GetMapping("/follower")
    public ResponseEntity<List<UserDto>> selectAllFollower(){
        List<UserDto> followerList = followService.selectAllFollower();
        return new ResponseEntity<>(followerList, HttpStatus.OK);
    }

    //팔로우 대기 삭제
    @DeleteMapping("/follow-wait/{nickname}")
    public ResponseEntity<String> deleteFollowWait(@PathVariable String follow){
        followService.deleteFollowWait(follow);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    @DeleteMapping("/following/{nickname}")
    public ResponseEntity<String> deleteFollowing(@PathVariable String follow){
        followService.deleteFollowing(follow);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    @DeleteMapping("/follower/{nickname}")
    public ResponseEntity<String> deleteFollower(@PathVariable String follow){
        followService.deleteFollower(follow);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }








}
