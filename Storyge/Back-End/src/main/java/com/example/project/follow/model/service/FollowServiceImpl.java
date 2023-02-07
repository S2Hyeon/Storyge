package com.example.project.follow.model.service;

import com.example.project.follow.model.dto.UserNicknameDto;
import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.follow.model.repository.FollowWaitRepository;
import com.example.project.notification.model.dto.NotificationFollowDto;
import com.example.project.notification.model.service.NotificationService;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final FollowWaitRepository followWaitRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    // 팔로우 대기 신청
    @Override
    public Boolean insertFollowWait(UserNicknameDto following) {
//        User currentUser = null;
        User currentUser = userRepository.findById((long)1).orElse(null); // 나
        System.out.println(currentUser);
//        Optional<User> followingUser = userRepository.findById(following.getUserId());
        User followingUser = userRepository.findByNickname(following.getNickname()).orElse(null); // 팔로우 신청할 사람

        if(followingUser==null){
            return false;
        }

        FollowWait checkExist = followWaitRepository.findByFollowingAndAndUserId(followingUser, currentUser); // 이미 신청 내역이 존재하는지 확인
        if (checkExist != null) { // 존재하면 false 반환
            return false;
        }
        else{ // 신청 내역이 존재하지 않으면
            if(followRepository.findByFollowingAndFollower(followingUser, currentUser)!=null){ // 팔로우 중인지 확인
                return false; // 팔로우 중이면 false 반환
            }
            else{// 신청 상태도 아니고 팔로우 중도 아니라면 팔로우 신청 보냄
                followWaitRepository.save(FollowWait.builder()
                        .userId(currentUser)
                        .following(followingUser)
                        .build());

        // 알림 table에 insert
        notificationService.insertFollowWaitNotification(NotificationFollowDto.builder()
                .userId(followingUser.getUserId())
                .follow(currentUser.getUserId())
                .build());

                return true;
            }
        }

    }

    // 팔로우 수락
    @Override
    public Boolean insertFollower(UserNicknameDto follower) {
        User currentUser = userRepository.findById((long)1).orElse(null); // 나
//        Optional<User> followerUser = userRepository.findById(follower.getUserId());
        User followerUser = userRepository.findByNickname(follower.getNickname()).orElse(null); // 팔로우 신청한 사람

        if(followerUser==null){
            return false;
        }

        if(followRepository.findByFollowingAndFollower(currentUser, followerUser)!=null){ // 이미 상대가 팔로우 중임
            return false;
        }

        // 대기 상태에서 삭제
        followWaitRepository.deleteByFollowingAndUserId(followerUser, currentUser);
//        User following = userRepository.getOne(userDto.getUserId());

        // follow table에 insert (팔로우 수락)
        followRepository.save(Follow.builder()
                .following(currentUser)
                .follower(followerUser)
                .build());

        // 팔로우 수락 알림 -> 알림 table에 insert
        notificationService.insertFollowNotification(NotificationFollowDto.builder()
                .userId(followerUser.getUserId())
                .follow(currentUser.getUserId())
                .build());
        return true;
    }

    // 팔로우 대기 목록
    @Override
    public List<UserDto> selectAllFollowWait() {

//        Long currentUserId = (long)1;
        User currentUser = userRepository.findById((long)1).orElse(null); // 나

        List<FollowWait> followWaitList = followWaitRepository.findByFollowing(currentUser); // 팔로우 대기중인 사람들 목록 가져오기
        List<UserDto> followWaitUserList = new ArrayList<>();

        for(FollowWait follow : followWaitList){

            User user = follow.getUserId();

            // 팔로우 대기중인 사람들 userId, 프로필 사진, nickname 반환
            followWaitUserList.add(UserDto.builder()
                    .userId(user.getUserId())
                    .profileImg(user.getProfileImg())
                    .nickname(user.getNickname())
                    .build());
        }
//        List<FollowInfoDto> followWaitUserList = new ArrayList<>();
//        for(FollowWait follow : followWaitList){
//            User user = follow.getUserId();
//            UserDto userDto = UserDto.builder()
//                            .userId(user.getUserId())
//                            .nickname(user.getNickname())
//                            .profileImg(user.getProfileImg())
//                            .build();
//
//            followWaitUserList.add(FollowInfoDto.builder()
//                    .followId(follow.getWaitingId())
//                    .userDto(userDto)
//                    .build());
//        }

        return followWaitUserList;
    }

    //내 팔로잉 목록
    @Override
    public List<UserDto> selectAllFollowing() {
        //내가 팔로워인 사람들 리스트 가져오기
//        Long currentUserId = (long)1;
        User currentUser = userRepository.findById((long)1).orElse(null);

        List<Follow> followingList = followRepository.findByFollower(currentUser); //내 팔로잉 목록 가져오기
        List<UserDto> followerUserList = new ArrayList<>();
        for(Follow follow : followingList){

            User user = follow.getFollowing();
            // 팔로우 대기중인 사람들 userId, 프로필 사진, nickname 반환
            followerUserList.add(UserDto.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .profileImg(user.getProfileImg())
                    .build());
        }

//        List<FollowInfoDto> followerUserList = new ArrayList<>();
//        for(Follow follow : followingList){
//            User user = follow.getFollowing();
//            UserDto userDto = UserDto.builder()
//                    .userId(user.getUserId())
//                    .nickname(user.getNickname())
//                    .profileImg(user.getProfileImg())
//                    .build();
//
//            followerUserList.add(FollowInfoDto.builder()
//                    .followId(follow.getFollowId())
//                    .userDto(userDto)
//                    .build());
//        }
//
//
        return followerUserList;
    }

    //내 팔로워 목록
    @Override
    public List<UserDto> selectAllFollower() {
        //내가 팔로잉에 있는 리스트
//        Long currentUserId = (long)1;
        User currentUser = userRepository.findById((long)1).orElse(null);

        List<Follow> followingList = followRepository.findByFollowing(currentUser);
        List<UserDto> followerUserList = new ArrayList<>();

        for(Follow follow : followingList){

            User user = follow.getFollower();
            // 팔로워인 사람들 userId, 프로필 사진, nickname 반환
            followerUserList.add(UserDto.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .profileImg(user.getProfileImg())
                    .build());
        }


//        List<FollowInfoDto> followingUserList = new ArrayList<>();
//        for(Follow follow : followingList){
//            User user = follow.getFollower();
//            UserDto userDto = UserDto.builder()
//                    .userId(user.getUserId())
//                    .nickname(user.getNickname())
//                    .profileImg(user.getProfileImg())
//                    .build();
//
//            followingUserList.add(FollowInfoDto.builder()
//                    .followId(follow.getFollowId())
//                    .userDto(userDto)
//                    .build());
//        }

        return followerUserList;
    }

    //팔로우 거절(대기 상태 삭제)
    @Override
    public Boolean deleteFollowWait(String nickname) {
//        long currentUserId = (long)1; //나
        User currentUser = userRepository.findById((long)1).orElse(null);
        User selectFollower = userRepository.findByNickname(nickname).orElse(null);

        if(selectFollower==null){
            return false;
        }
//        long followingWaitUser = wait.getUserId(); //나에게 신청을 한사람
        followWaitRepository.deleteByFollowingAndUserId(currentUser, selectFollower);
        return true;
    }

    @Override
    public Boolean deleteFollowing(String nickname) {
        //언팔로우 하기
        //내가 follower 지우려는 상대가 following
//        long currentUserId = (long)1; //나
        User currentUser = userRepository.findById((long)1).orElse(null); //나: follower
        User selectFollowing = userRepository.findByNickname(nickname).orElse(null); // 지우려는 상대: following
        if(selectFollowing==null){
            return false;
        }

        if(followRepository.findByFollowingAndFollower(selectFollowing,currentUser)==null){ // 만약 팔로우 중이 아니거나 상대가 먼저 삭제 해버림
            return false;
        }
//        long following = follow.getUserId();
        followRepository.deleteByFollowingAndFollower(selectFollowing, currentUser);
        return true;
    }

    @Override
    public Boolean deleteFollower(String nickname) {
        //팔로워 삭제하기
        //내가 following 지우려는 상대가 follower
//        long currentUserId = (long)1; //나
//        long follower = follow.getUserId();
        User currentUser = userRepository.findById((long)1).orElse(null); //나 : following
        User selectFollower = userRepository.findByNickname(nickname).orElse(null); // 지우려는 상대: follower

        if(selectFollower==null){
            return false;
        }

        if(followRepository.findByFollowingAndFollower(currentUser, selectFollower)==null){ // 팔로워가 아니거나 상대가 먼저 언팔로우
            return false;
        }

        followRepository.deleteByFollowingAndFollower(currentUser, selectFollower);
        return true;

    }
}

