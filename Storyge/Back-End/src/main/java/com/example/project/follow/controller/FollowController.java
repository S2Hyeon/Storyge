package com.example.project.follow.controller;

import com.example.project.follow.model.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class FollowController {

    private final FollowService followService;



}
