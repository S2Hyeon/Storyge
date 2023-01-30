package com.example.project.user.model.service;

import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

//    @Override
//    public OauthToken getAccessToken(String code) {
//        return null;
//    }

    @Override
    public String saveUserAndGetToken(String token) {
        return null;
    }

    @Override
    public String createToken(UserDto userDto) {
        return null;
    }

    @Override
    public Optional<User> getUser(HttpServletRequest request) {
        return Optional.empty();
    }

    @Override
    public void modifyUser(UserUpdateParam param) {

    }
}
