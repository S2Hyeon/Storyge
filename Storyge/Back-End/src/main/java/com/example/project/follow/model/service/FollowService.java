package com.example.project.follow.model.service;

import com.example.project.follow.model.dto.UserIdDto;
import com.example.project.user.model.dto.UserDto;

import java.util.List;

public interface FollowService {

    //follow 대기(following 신청)
    Boolean insertFollowWait(Long userId, UserIdDto following);
    // follow 등록(follower 수락)
    Boolean insertFollower(Long userId, UserIdDto follower);

    // 팔로우 대기 목록
    List<UserDto> selectAllFollowWait(Long userId);
    //팔로잉 목록
    List<UserDto> selectAllFollowing(Long userId);
    //팔로워 목록
    List<UserDto> selectAllFollower(Long userId);
    //팔로우 대기 삭제
    Boolean deleteFollowWait(Long userId, Long follow);
    //팔로잉 삭제
    Boolean deleteFollowing(Long userId, Long follow);
    //팔로우 삭제
    Boolean deleteFollower(Long userId, Long follow);



}
