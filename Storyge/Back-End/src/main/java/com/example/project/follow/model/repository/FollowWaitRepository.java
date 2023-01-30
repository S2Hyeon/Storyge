package com.example.project.follow.model.repository;

import com.example.project.follow.model.dto.FollowUserDto;
import com.example.project.follow.model.entity.FollowWait;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowWaitRepository extends JpaRepository<FollowWait, Long> {


}
