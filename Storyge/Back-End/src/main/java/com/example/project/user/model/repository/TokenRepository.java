package com.example.project.user.model.repository;

import com.example.project.user.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByUserId(Long userId);
}
