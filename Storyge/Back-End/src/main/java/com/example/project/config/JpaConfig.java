package com.example.project.config;

import com.example.project.user.model.repository.UserRepository;
import com.example.project.user.model.service.UserService;
import com.example.project.user.model.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public UserService userService(){
        return new UserServiceImpl(bCryptPasswordEncoder, userRepository);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
