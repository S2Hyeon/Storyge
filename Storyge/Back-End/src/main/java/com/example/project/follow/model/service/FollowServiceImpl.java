package com.example.project.follow.model.service;

import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.follow.model.repository.FollowWaitRepository;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final FollowWaitRepository followWaitRepository;

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
        List<FollowWait> followWaitList = followWaitRepository.findAll();


        return null;
    }

    @Override
    public List<UserDto> selectAllFollowing(UserDto userId) {
        return null;
    }

    @Override
    public List<UserDto> selectAllFollower(UserDto userId) {
        return null;
    }

    @Override
    public void deleteFollowWait(UserDto waitId) {

    }

    @Override
    public void deleteFollowing(UserDto followId) {

    }

    @Override
    public void deleteFollower(UserDto followId) {

    }
}

