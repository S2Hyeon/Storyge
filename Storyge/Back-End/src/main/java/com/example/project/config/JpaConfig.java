package com.example.project.config;

import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.user.model.Service.UserService;
import com.example.project.user.model.Service.UserServiceImpl;
import com.example.project.user.model.jwt.JwtUtil;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository, followRepository);
    }
}
