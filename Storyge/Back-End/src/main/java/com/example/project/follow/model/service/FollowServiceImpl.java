package com.example.project.follow.model.service;

import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.follow.model.repository.FollowWaitRepository;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final FollowWaitRepository followWaitRepository;
    private final UserRepository userRepository;

    @Override
    public void insertFollowWait(UserDto userDto) {
        User currentUser = null;
//        User following = userRepository.getOne(userDto.getUserId());
        followWaitRepository.save(FollowWait.builder()
                .userId(currentUser)
//                .following(following)
                .build());
    }

    @Override
    public void insertFollower(UserDto userDto) {
        User currentUser = null;
//        User following = userRepository.getOne(userDto.getUserId());

        followRepository.save(Follow.builder()
//                .following(currentUser.getUserId())
//                .follower(following)
                .build());

    }

    @Override
    public List<UserDto> selectAllFollowWait() {

        Long currentUserId = (long)0;
        List<FollowWait> followWaitList = followWaitRepository.findByFollowing(currentUserId);
        List<UserDto> followWaitUserDtoList = new ArrayList<>();
        for(FollowWait follow : followWaitList){
            User user = follow.getUserId();
            followWaitUserDtoList.add(UserDto.builder()
                    .userId(user.getUserId())
                    .profileImg(user.getProfileImg())
                    .nickname(user.getNickname())
                    .build());
        }

        return followWaitUserDtoList;
    }

    //팔로잉 목록
    @Override
    public List<UserDto> selectAllFollowing(UserDto userId) {
        //내가 팔로워인 사람들 리스트 가져오기
        Long currentUserId = (long)0;
        List<Follow> followingList = followRepository.findByFollower(currentUserId);
        List<UserDto> followingUserDtoList = new ArrayList<>();

        for(Follow follow : followingList){
            User user = follow.getFollowing();
            followingUserDtoList.add(UserDto.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .profileImg(user.getProfileImg())
                    .build());
        }

        return followingUserDtoList;
    }

    //팔로워 목록
    @Override
    public List<UserDto> selectAllFollower(UserDto userId) {
        //내가 팔로잉에 있는 리스트
        Long currentUserId = (long)0;
        List<Follow> followingList = followRepository.findByFollowing(currentUserId);
        List<UserDto> followerUserDtoList = new ArrayList<>();
        for(Follow follow : followingList){
            User user = follow.getFollowing();
            followerUserDtoList.add(UserDto.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .profileImg(user.getProfileImg())
                    .build());
        }


        return followerUserDtoList;
    }

    @Override
    public void deleteFollowWait(UserDto wait) {
        long currentUserId = (long)0; //나
        long followingWaitUser = wait.getUserId(); //나에게 신청을 한사람
        followWaitRepository.deleteByFollowingAndUserId(currentUserId, followingWaitUser);
    }

    @Override
    public void deleteFollowing(UserDto follow) {
        //언팔로우 하기
        //내가 follower 지우려는 상대가 following
        long currentUserId = (long)0; //나
        long following = follow.getUserId();
        followRepository.deleteByFollowingAndFollower(following, currentUserId);
    }

    @Override
    public void deleteFollower(UserDto follow) {
        //팔로워 삭제하기
        //내가 following 지우려는 상대가 follower
        long currentUserId = (long)0; //나
        long follower = follow.getUserId();
        followRepository.deleteByFollowingAndFollower(follower, currentUserId);

    }
}

