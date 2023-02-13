package com.example.project.user.controller;

import com.example.project.follow.model.service.FollowService;
import com.example.project.user.model.Service.UserService;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.file.FileService;
import com.example.project.user.model.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.project.user.model.jwt.JwtProperties.TOKEN_HEADER;

@RestController
@RequiredArgsConstructor
@Api(tags = {"사용자 관련 API"})
public class UserController {
    private static final String SUCCESS = "success";
    //    private final String FAIL = "fail";
    private final UserService userService;
    private final FollowService followService;
    private final FileService fileService;
    private final JwtUtil jwtUtil;

    @ApiOperation(value = "사용자 정보 수정", notes = "본인의 닉네임 또는 프로필 사진을 수정")
    @PutMapping(value = "/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserInfo(HttpServletRequest request, @RequestParam(value = "image") MultipartFile multipartFile, @RequestParam(value = "nickname") String nickname) throws IOException {

        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        String url = fileService.upload(multipartFile, "profile");
        userService.updateUser(userId, nickname, url);
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
    @GetMapping("/user/{otherId}")
    public ResponseEntity<Map<String, Object>> selectOtherUser(HttpServletRequest request, @PathVariable Long otherId) {
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));
        Map<String, Object> data = new HashMap<>();
        data.put("user", userService.selectOneUser(otherId));
        data.put("scope", followService.checkFollowWait(userId, otherId));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자를 닉네임으로 검색한다")
    @GetMapping("/user/search/{nickname}")
    public ResponseEntity<?> searchUser(HttpServletRequest request, @PathVariable String nickname) {
        Long userId = jwtUtil.getUserId(request.getHeader(TOKEN_HEADER));

        return new ResponseEntity<>(userService.searchUser(nickname, userId), HttpStatus.OK);
    }

    @ApiOperation(value = "사용자를 닉네임 중복 검사")
    @GetMapping("/user/check/{nickname}")
    public ResponseEntity<?> checkNickname(@PathVariable String nickname) {
        return new ResponseEntity<>(userService.checkNickname(nickname), HttpStatus.OK);
    }

}
