package com.example.project.follow.model.service;

import com.example.project.follow.model.dto.FollowDto;
import com.example.project.follow.model.dto.FollowUserDto;
import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;

public interface FollowService {

    //follow 대기(following 신청)
    void insertFollowWait(long following);
    // follow 등록(follower 수락)
    void insertFollower(long following);
    //팔로우 대기 목록
//    List<UserDto> selectAllFollowWate(Long userId);
//    //팔로잉 목록
//    List<UserDto> selectAllFollowing(Long userId);
//    //팔로워 목록
//    List<UserDto> selectAllFollower(Long userId);
    //팔로우 대기 삭제
    void deleteFollowWait(long following);
    //팔로잉 삭제
    void deleteFollowing(long following);
    //팔로우 삭제
    void deleteFollower(long follower);


}
