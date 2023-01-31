package com.example.project.user.model.repository;

import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByEmail(String email);

    Boolean existsByEmail(String email);
}
