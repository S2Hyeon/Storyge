package com.example.project.follow.model.repository;

import com.example.project.follow.model.entity.FollowWait;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowWaitRepository extends JpaRepository<FollowWait, Long> {
//    List<UserDto> findFollowWaitUserList(long userId);
//    List<FollowWait> findByFollowing(long following);

    List<FollowWait> findByFollowing(User following); //나의 팔로우 수락을 대기하는 사람들 리스트

    FollowWait findByFollowingAndUserId(long following, long userId);

    //대기 상태 삭제
//    void deleteByFollowingAndUserId(long following, long userId);
    void deleteByFollowingAndUserId(User following, User userId);


}
