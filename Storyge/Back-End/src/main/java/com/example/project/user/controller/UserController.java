//package com.example.project.user.controller;
//
//import com.example.project.user.model.Service.UserService;
//import com.example.project.user.model.dto.UserUpdateParam;
//import com.example.project.user.model.jwt.JwtProperties;
//import com.example.project.user.model.jwt.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Objects;
//
//@RestController
//@RequiredArgsConstructor
//public class UserController {
//    private final String SUCCESS = "success";
//    private final String FAIL = "fail";
//    private final UserService userService;
//    private final JwtUtil jwtUtil;
//
//    @PutMapping("/user")
//    public ResponseEntity<?> updateUserInfo(HttpServletRequest request, @RequestBody UserUpdateParam param) {
//        Long userId = jwtUtil.getUserId(request.getHeader(JwtProperties.HEADER_STRING));
//
//        if (Objects.equals(param.getUserId(), userId)) {
//            userService.updateUser(param);
//        }
//
//        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
//    }
//
//}
