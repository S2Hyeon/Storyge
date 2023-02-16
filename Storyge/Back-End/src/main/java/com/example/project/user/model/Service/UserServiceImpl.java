package com.example.project.user.model.Service;

import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Override
    public void updateUser(Long userId, String nickname, String profileUrl) {
        User user = userRepository.findById(userId).orElseThrow();
        //만약 넘어온 프로필만 null일 경우, 닉네임만 바꿔주기
        if (Objects.isNull(profileUrl))
            user.update(nickname, user.getProfileImg());
            //만약 넘어온 nickname만 null일 경우, 프로필만 바꿔주기
        else if (Objects.isNull(nickname))
            user.update(user.getNickname(), profileUrl);

            //둘다 제대로 넘어왔으면 둘다 변경
        else
            user.update(nickname, profileUrl);

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

    @Override
    public List<UserDto> searchUser(String nickname, Long userId) {
        String myNickname = userRepository.findById(userId).get().getNickname();
        return userRepository.findByNicknameContainingAndNicknameNotLike(nickname, myNickname).stream().map(this::toDto).collect(Collectors.toList());
    }

    //닉네임 중복검사
    @Override
    public boolean checkNickname(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }
}
