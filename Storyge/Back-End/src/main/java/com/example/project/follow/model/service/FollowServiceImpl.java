package com.example.project.follow.model.service;

import com.example.project.follow.model.dto.FollowUserInfoDto;
import com.example.project.follow.model.dto.UserIdDto;
import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.follow.model.repository.FollowWaitRepository;
import com.example.project.notification.model.dto.NotificationFollowDto;
import com.example.project.notification.model.service.NotificationService;
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
    public Boolean insertFollowWait(Long userId, UserIdDto following) {
        Long followingUserId = following.getUserId();

//        User currentUser = userRepository.findById(userId).orElse(null); // 나
        User followingUser = userRepository.findById(followingUserId).orElse(null); // 팔로우 신청할 사람

        if(followingUser==null){
            return false;
        }

        FollowWait checkExist = followWaitRepository.findByFollowingAndAndUserId(followingUserId, userId); // 이미 신청 내역이 존재하는지 확인
        if (checkExist != null) { // 존재하면 false 반환
            return false;
        }
        else{ // 신청 내역이 존재하지 않으면
            if(followRepository.findByFollowingAndFollower(followingUserId, userId)!=null){ // 팔로우 중인지 확인
                return false; // 팔로우 중이면 false 반환
            }
            else{// 신청 상태도 아니고 팔로우 중도 아니라면 팔로우 신청 보냄
                followWaitRepository.save(FollowWait.builder()
                        .userId(userId)
                        .following(followingUserId)
                        .build());

                // 알림 table에 insert
                notificationService.insertFollowWaitNotification(
                        NotificationFollowDto.builder()
                                .userId(followingUserId) // 이 사람에게 알림이 간다
                                .follow(userId) // 이 사람이 알림을 보냄
                                .build());

                return true;
            }
        }

    }

    // 팔로우 수락
    @Override
    public Boolean insertFollower(Long userId, UserIdDto follower) {

        Long followerUserId = follower.getUserId();

        User currentUser = userRepository.findById(userId).orElse(null); // 나
        User followerUser = userRepository.findById(followerUserId).orElse(null); // 팔로우 신청한 사람

        if (followerUser == null) {
            return false;
        }

        if (followRepository.findByFollowingAndFollower(userId, followerUserId) != null) { // 이미 상대가 팔로우 중임
            return false;
        }

        // 대기 상태에서 삭제
        followWaitRepository.deleteByFollowingAndUserId(userId,followerUserId);

        // follow table에 insert (팔로우 수락)
        followRepository.save(Follow.builder()
                .following(userId)
                .follower(followerUserId)
                .build());

        // 팔로우 수락 알림 -> 알림 table에 insert
        notificationService.insertFollowNotification(
                NotificationFollowDto.builder()
                        .userId(followerUserId) // 이사람에게
                        .follow(userId) // 이 사람이 수락했다고 알림이 간다
                        .build());

        return true;
    }

    // 팔로우 대기 목록
    @Override
    public List<FollowUserInfoDto> selectAllFollowWait(Long userId) {

//        User currentUser = userRepository.findById(userId).orElse(null); // 나

        // 나에게 팔로우 신청을 건 사람들을 찾는다
        List<FollowWait> followWaitList = followWaitRepository.findAllByFollowing(userId); // 팔로우 대기중인 사람들 목록 가져오기
        List<FollowUserInfoDto> followWaitUserList = new ArrayList<>(); // 신청한 사람들의 정보를 찾아서 담음

        for(FollowWait follow : followWaitList){

            User user = follow.getUser();

            // 나에게 팔로우 신청을 한 사람들 userId, 프로필 사진, nickname 반환
            followWaitUserList.add(FollowUserInfoDto.builder()
                    .userId(user.getUserId())
                    .profileImg(user.getProfileImg())
                    .nickname(user.getNickname())
                    .build());
        }

        return followWaitUserList;
    }

    //내 팔로잉 목록
    @Override
    public List<FollowUserInfoDto> selectAllFollowing(Long userId) {

//        User currentUser = userRepository.findById(userId).orElse(null);

        //내가 팔로워인 사람들 리스트 가져오기
        List<Follow> followingList = followRepository.findAllByFollower(userId); //내 팔로잉 목록 가져오기
        List<FollowUserInfoDto> followerUserList = new ArrayList<>(); // 팔로잉들의 정보만 찾아서 담기
        for(Follow follow : followingList){

            User user = follow.getFollowingUsers();
            // 팔로우중인 사람들 userId, 프로필 사진, nickname 반환
            followerUserList.add(FollowUserInfoDto.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .profileImg(user.getProfileImg())
                    .build());
        }

        return followerUserList;
    }

    //내 팔로워 목록
    @Override
    public List<FollowUserInfoDto> selectAllFollower(Long userId) {

//        User currentUser = userRepository.findById(userId).orElse(null);

        //내가 팔로잉에 있는 리스트, 나를 팔로우 하는 사람들을 찾는다
        List<Follow> followingList = followRepository.findAllByFollowing(userId);
        List<FollowUserInfoDto> followerUserList = new ArrayList<>(); // 팔로워들의 정보를 담는다

        for (Follow follow : followingList) {

            User user = userRepository.findById(follow.getFollower()).orElse(null);

            // 팔로워인 사람들 userId, 프로필 사진, nickname 반환
            followerUserList.add(FollowUserInfoDto.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .profileImg(user.getProfileImg())
                    .build());
        }



        return followerUserList;
    }

    //팔로우 거절(대기 상태 삭제)
    @Override
    public Boolean deleteFollowWait(Long userId, Long follow) {

//        User currentUser = userRepository.findById(userId).orElse(null); // 나
//        User selectFollower = userRepository.findById(follow).orElse(null); // 삭제할 사람

//        if(selectFollower==null){
//            return false;
//        }
//        long followingWaitUser = wait.getUserId(); //나에게 신청을 한사람
        followWaitRepository.deleteByFollowingAndUserId(userId, follow);
        return true;
    }

    @Override
    public Boolean deleteFollowing(Long userId, Long follow) {
        //언팔로우 하기
        //내가 follower 지우려는 상대가 following
        User currentUser = userRepository.findById(userId).orElse(null); //나: follower
        User selectFollowing = userRepository.findById(follow).orElse(null); // 지우려는 상대: following
        if(selectFollowing==null){
            return false;
        }

        if(followRepository.findByFollowingAndFollower(follow,userId)==null){ // 만약 팔로우 중이 아니거나 상대가 먼저 삭제 해버림
            return false;
        }
//        long following = follow.getUserId();
        followRepository.deleteByFollowingAndFollower(follow, userId);
        return true;
    }

    @Override
    public Boolean deleteFollower(Long userId, Long follow) {
        //팔로워 삭제하기
        //내가 following 지우려는 상대가 follower
        User currentUser = userRepository.findById(userId).orElse(null); //나 : following
        User selectFollower = userRepository.findById(follow).orElse(null); // 지우려는 상대: follower

        if(selectFollower==null){
            return false;
        }

        if(followRepository.findByFollowingAndFollower(userId, follow)==null){ // 팔로워가 아니거나 상대가 먼저 언팔로우
            return false;
        }

        followRepository.deleteByFollowingAndFollower(userId, follow);
        return true;

    }
}

