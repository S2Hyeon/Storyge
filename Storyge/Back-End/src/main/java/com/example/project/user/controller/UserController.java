package com.example.project.user.controller;

import com.example.project.user.model.Service.UserService;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.dto.UserUpdateParam;
import com.example.project.user.model.jwt.JwtProperties;
import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.example.project.user.model.jwt.JwtProperties.AUTHORITIES_KEY;
import static com.example.project.user.model.jwt.JwtProperties.TOKEN_PREFIX;

@RestController
@RequiredArgsConstructor
@Api(tags = {"사용자 관련 API"})
public class UserController {
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final UserService userService;
    private final JwtUtil jwtUtil;
    
    @ApiOperation(value = "사용자 정보 수정", notes = "본인의 닉네임 또는 프로필 사진을 수정")
    @PutMapping("/user")
    public ResponseEntity<?> updateUserInfo(@RequestHeader(name = TOKEN_PREFIX)String request, @RequestBody UserUpdateParam param) {

        if (Objects.equals(param.getUserId(), param.getUserId())) {
            userService.updateUser(param);
        }

        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

}
