package com.example.project.follow.model.repository;

import com.example.project.follow.model.entity.FollowWait;
import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowWaitRepository extends JpaRepository<FollowWait, Long> {
//    List<UserDto> findFollowWaitUserList(long userId);
//    List<FollowWait> findByFollowing(long following);

    List<FollowWait> findByFollowing(User following); //나의 팔로우 수락을 대기하는 사람들 리스트
    FollowWait findByFollowingAndAndUserId(User following, User userId); // 이미 신청이 존재하는지 확인
    //대기 상태 삭제
    void deleteByFollowingAndUserId(User following, User userId);


}
