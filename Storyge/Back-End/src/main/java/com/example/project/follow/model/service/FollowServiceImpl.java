package com.example.project.follow.model.service;

import com.example.project.follow.model.dto.FollowUserDto;
import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.follow.model.repository.FollowWaitRepository;
import com.example.project.user.model.dto.UserDto;
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
    public void insertFollowWait(long following) {

    }

    @Override
    public void insertFollower(long follower) {

    }

    @Override
    public List<UserDto> selectAllFollowWate(long userId) {
        return null;
    }

    @Override
    public List<UserDto> selectAllFollowing(long userId) {
        return null;
    }

    @Override
    public List<UserDto> selectAllFollower(long userId) {
        return null;
    }

    @Override
    public void deleteFollowWait(long waitId) {

    }

    @Override
    public void deleteFollowing(long followId) {

    }

    @Override
    public void deleteFollower(long followId) {

    }
}
