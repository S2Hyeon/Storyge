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

        User currentUser = userRepository.findById(userId).orElse(null); // 나
        User followingUser = userRepository.findById(following.getUserId()).orElse(null); // 팔로우 신청할 사람

//        if(followingUser==null){
//            return false;
//        }

        FollowWait checkExist = followWaitRepository.findByFollowingAndAndUserId(following.getUserId(), userId); // 이미 신청 내역이 존재하는지 확인
        if (checkExist != null) { // 존재하면 false 반환
            return false;
        }
        else{ // 신청 내역이 존재하지 않으면
            if(followRepository.findByFollowingAndFollower(following.getUserId(), userId)!=null){ // 팔로우 중인지 확인
                return false; // 팔로우 중이면 false 반환
            }
            else{// 신청 상태도 아니고 팔로우 중도 아니라면 팔로우 신청 보냄
                followWaitRepository.save(FollowWait.builder()
                        .userId(userId)
                        .following(following.getUserId())
                        .build());

                // 알림 table에 insert
                notificationService.insertFollowWaitNotification(
                        NotificationFollowDto.builder()
                                .userId(followingUser.getUserId())
                                .follow(currentUser.getUserId())
                                .build());

                return true;
            }
        }

    }

    // 팔로우 수락
    @Override
    public Boolean insertFollower(Long userId, UserIdDto follower) {

        User currentUser = userRepository.findById(userId).orElse(null); // 나
        User followerUser = userRepository.findById(follower.getUserId()).orElse(null); // 팔로우 신청한 사람

        if (followerUser == null) {
            return false;
        }

        if (followRepository.findByFollowingAndFollower(userId, follower.getUserId()) != null) { // 이미 상대가 팔로우 중임
            return false;
        }

        // 대기 상태에서 삭제
        followWaitRepository.deleteByFollowingAndUserId(userId,follower.getUserId());

        // follow table에 insert (팔로우 수락)
        followRepository.save(Follow.builder()
                .following(userId)
                .follower(follower.getUserId())
                .build());

        // 팔로우 수락 알림 -> 알림 table에 insert
        notificationService.insertFollowNotification(
                NotificationFollowDto.builder()
                        .userId(followerUser.getUserId())
                        .follow(currentUser.getUserId())
                        .build());

        return true;
    }

    // 팔로우 대기 목록
    @Override
    public List<FollowUserInfoDto> selectAllFollowWait(Long userId) {

        User currentUser = userRepository.findById(userId).orElse(null); // 나

        List<FollowWait> followWaitList = followWaitRepository.findAllByFollowing(userId); // 팔로우 대기중인 사람들 목록 가져오기
        List<FollowUserInfoDto> followWaitUserList = new ArrayList<>();

        for(FollowWait follow : followWaitList){

            User user = follow.getFollowWaitUser();

            // 팔로우 대기중인 사람들 userId, 프로필 사진, nickname 반환
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

        //내가 팔로워인 사람들 리스트 가져오기
//        User currentUser = userRepository.findById(userId).orElse(null);

        List<Follow> followingList = followRepository.findAllByFollower(userId); //내 팔로잉 목록 가져오기
        List<FollowUserInfoDto> followerUserList = new ArrayList<>();
        for(Follow follow : followingList){

            User user = follow.getFollowingUsers();
            // 팔로우 대기중인 사람들 userId, 프로필 사진, nickname 반환
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

        //내가 팔로잉에 있는 리스트
//        User currentUser = userRepository.findById(userId).orElse(null);

        List<Follow> followingList = followRepository.findAllByFollowing(userId);
        List<FollowUserInfoDto> followerUserList = new ArrayList<>();

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

