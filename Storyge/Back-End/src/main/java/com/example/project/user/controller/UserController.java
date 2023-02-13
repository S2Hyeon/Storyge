package com.example.project.user.controller;

import com.example.project.user.model.Service.UserService;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.dto.UserUpdateParam;
import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_HEADER;

@RestController
@RequiredArgsConstructor
@Api(tags = {"User 관련 controller"})
public class UserController {
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PutMapping("/user")
    public ResponseEntity<?> updateUserInfo(HttpServletRequest request, @RequestBody UserUpdateParam param) {

        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));

        if (Objects.equals(userId, param.getUserId())) {
            userService.updateUser(param);
        }

        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    //내 정보 불러오기 -> 이름, 프로필, 팔로워/팔로잉 수
    @GetMapping("/user")
    public ResponseEntity<UserDto> selectOneUser(HttpServletRequest request) {
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));

        return new ResponseEntity<>(userService.selectOneUser(userId), HttpStatus.OK);
    }

    //상대 정보 불러오기 -> 이름, 프로필, 팔로워/팔로잉 수
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> selectOtherUser(@PathVariable Long userId) {

        return new ResponseEntity<>(userService.selectOneUser(userId), HttpStatus.OK);
    }
}
