package com.example.project.follow.model.repository;

import com.example.project.follow.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollower(long follower);
    List<Follow> findByFollowing(long following);
    void deleteByFollowingAndFollower(long following, long follower);
}
