package com.example.project.follow.model.repository;

import com.example.project.follow.model.entity.Follow;
import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowing(User following);
    void deleteByFollowingAndFollower(User following, User follower);

}
