package com.example.project.follow.model.service;

import com.example.project.follow.model.dto.FollowDto;
import com.example.project.follow.model.dto.FollowUserDto;
import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;
import com.example.project.user.model.dto.UserDto;

import java.util.List;

public interface FollowService {

    //follow 대기(following 신청)
    void insertFollowWait(long following);
    // follow 등록(follower 수락)
    void insertFollower(long follower);

    // 팔로우 대기 목록
    List<UserDto> selectAllFollowWate(long userId);
    //팔로잉 목록
    List<UserDto> selectAllFollowing(long userId);
    //팔로워 목록
    List<UserDto> selectAllFollower(long userId);
    //팔로우 대기 삭제
    void deleteFollowWait(long waitId);
    //팔로잉 삭제
    void deleteFollowing(long followId);
    //팔로우 삭제
    void deleteFollower(long followId);


}
