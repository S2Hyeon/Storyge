package com.example.project.user.model.Service;

import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.dto.UserUpdateParam;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Override
    public void updateUser(Long userId, UserUpdateParam param) {
        User user = userRepository.findById(userId).orElseThrow();
        user.update(param.getNickname(), param.getProfile());
    }

    @Override
    public UserDto selectOneUser(Long userId) {

        //팔로워 찾기
        Long follower = followRepository.countByFollowing(userId);

        //팔로잉 찾기
        Long following = followRepository.countByFollower(userId);
        UserDto user = toDto(userRepository.findById(userId).orElseThrow());
        user.setFollower(follower);
        user.setFollowing(following);

        return user;
    }
}
