package com.example.project.user.controller;

import com.example.project.user.model.Service.UserService;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.dto.UserUpdateParam;
import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_HEADER;

@RestController
@RequiredArgsConstructor
@Api(tags = {"사용자 관련 API"})
public class UserController {
    private static final String SUCCESS = "success";
    //    private final String FAIL = "fail";
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @ApiOperation(value = "사용자 정보 수정", notes = "본인의 닉네임 또는 프로필 사진을 수정")
    @PutMapping("/user")
    public ResponseEntity<?> updateUserInfo(HttpServletRequest request, @RequestBody UserUpdateParam param) {

        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        userService.updateUser(userId, param);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    //내 정보 불러오기 -> 이름, 프로필, 팔로워/팔로잉 수
    @ApiOperation(value = "본인 정보 불러오기", notes = "본인의 이름, 프로필, 팔로워/팔로잉 수 정보")
    @GetMapping("/user")
    public ResponseEntity<UserDto> selectOneUser(HttpServletRequest request) {
        System.out.println("request header: " + request.getHeader(TOKEN_HEADER));
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        System.out.println("userId: " + userId);
        return new ResponseEntity<>(userService.selectOneUser(userId), HttpStatus.OK);
    }

    //상대 정보 불러오기 -> 이름, 프로필, 팔로워/팔로잉 수
    @ApiOperation(value = "다른 유저 정보 불러오기", notes = "다른 유저의 이름, 프로필, 팔로워/팔로잉 수 정보")
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> selectOtherUser(@PathVariable Long userId) {

        return new ResponseEntity<>(userService.selectOneUser(userId), HttpStatus.OK);
    }
}
