package com.example.project.follow.model.service;

import com.example.project.follow.model.dto.FollowUserDto;
import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.follow.model.repository.FollowWaitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final FollowWaitRepository followWaitRepository;

    @Override
    public void insertFollowWait(long following) {
//        followWaitRepository.save(followWaitToEntity(followUserDto));
        followWaitRepository.save(FollowWait.builder()
//                .follower()
//                .following()
                .build()
        );
    }

    @Override
    public void insertFollower(long following) {
//        followRepository.save(followUsertoEntity(followUserDto));
        followRepository.save(Follow.builder()
                .build()
        );
    }

//    @Override
//    public List<UserDto> selectAllFollowWate(Long userId) {
//        return null;
//    }
//
//    @Override
//    public List<UserDto> selectAllFollowing(Long userId) {
//        return null;
//    }
//
//    @Override
//    public List<UserDto> selectAllFollower(Long userId) {
//        return null;
//    }

    @Override
    public void deleteFollowWait(long following) {

    }

    @Override
    public void deleteFollowing(long following) {

    }

    @Override
    public void deleteFollower(long follower) {

    }
}
