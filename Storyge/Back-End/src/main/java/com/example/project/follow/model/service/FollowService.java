//package com.example.project.follow.model.service;
//
//import com.example.project.follow.model.dto.UserNicknameDto;
//import com.example.project.user.model.dto.UserDto;
//
//import java.util.List;
//
//public interface FollowService {
//
//    //follow 대기(following 신청)
//    Boolean insertFollowWait(UserNicknameDto following);
//    // follow 등록(follower 수락)
//    Boolean insertFollower(UserNicknameDto follower);
//
//    // 팔로우 대기 목록
//    List<UserDto> selectAllFollowWait();
//    //팔로잉 목록
//    List<UserDto> selectAllFollowing();
//    //팔로워 목록
//    List<UserDto> selectAllFollower();
//    //팔로우 대기 삭제
//    Boolean deleteFollowWait(String nickname);
//    //팔로잉 삭제
//    Boolean deleteFollowing(String nickname);
//    //팔로우 삭제
//    Boolean deleteFollower(String nickname);
//
//
//
//
//}
