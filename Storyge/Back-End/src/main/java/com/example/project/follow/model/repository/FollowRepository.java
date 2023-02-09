package com.example.project.follow.model.repository;

import com.example.project.follow.model.entity.Follow;
import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollower(Long follower);

    List<Follow> findAllByFollowing(Long following);

    Follow findByFollowingAndFollower(Long following, Long follower);

    void deleteByFollowingAndFollower(Long following, Long follower);

    //팔로잉 수 찾기
    Long countByFollower(Long userId);

    //팔로워 수 찾기
    Long countByFollowing(Long userId);
}
