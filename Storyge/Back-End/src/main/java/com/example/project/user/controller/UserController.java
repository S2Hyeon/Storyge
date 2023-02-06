package com.example.project.user.controller;

import com.example.project.user.model.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final UserService userService;
//    private final JwtUtil jwtUtil;

//    @PutMapping("/user")
//    public ResponseEntity<?> updateUserInfo(HttpServletRequest request, @RequestBody UserUpdateParam param) {
//        Long userId = jwtUtil.getUserId(request.getHeader(JwtProperties.TOKEN_PREFIX));
//
//        if (Objects.equals(param.getUserId(), userId)) {
//            userService.updateUser(param);
//        }
//
//        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
//    }

}
