package com.example.project.follow.model.repository;

import com.example.project.follow.model.entity.FollowWait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowWaitRepository extends JpaRepository<FollowWait, Long> {
    List<FollowWait> findAllByFollowing(Long following); //나의 팔로우 수락을 대기하는 사람들 리스트

    FollowWait findByFollowingAndAndUserId(Long following, Long userId); // 이미 신청이 존재하는지 확인

    //대기 상태 삭제
    void deleteByFollowingAndUserId(Long following, Long userId);


}
