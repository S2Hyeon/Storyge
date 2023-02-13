package com.example.project.user.model.repository;

import com.example.project.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByEmail(String email);

    List<User> findByNicknameContainingAndNicknameNotLike(String nickname, String myNickname);

}
