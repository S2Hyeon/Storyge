package com.example.project.follow.model.service;

import com.example.project.follow.model.dto.FollowDto;
import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;

import java.util.List;

public interface FollowService {

    //follow 대기(following 신청)
    void insertFollowWait(UserDto following);
    // follow 등록(follower 수락)
    void insertFollower(UserDto userDto);

    // 팔로우 대기 목록
    List<UserDto> selectAllFollowWait();
    //팔로잉 목록
    List<UserDto> selectAllFollowing(UserDto userId);
    //팔로워 목록
    List<UserDto> selectAllFollower(UserDto userId);
    //팔로우 대기 삭제
    void deleteFollowWait(UserDto waitId);
    //팔로잉 삭제
    void deleteFollowing(UserDto followId);
    //팔로우 삭제
    void deleteFollower(UserDto followId);


}
