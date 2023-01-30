package com.example.project.user.controller;

import com.example.project.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

//    @GetMapping("/oauth/token")
//    public ResponseEntity getLogin(@RequestParam("code") String code){
//        OauthToken oauthToken = userService.getAccessToken(code);
//    }
}
