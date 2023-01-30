package com.example.project.follow.model.repository;

import com.example.project.follow.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

}
