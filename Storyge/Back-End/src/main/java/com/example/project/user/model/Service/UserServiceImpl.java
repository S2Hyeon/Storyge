package com.example.project.user.model.Service;

import com.example.project.user.model.dto.UserUpdateParam;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void updateUser(UserUpdateParam param) {
        User user = userRepository.findById(param.getUserId()).orElseThrow();
        user.update(param.getNickname(), param.getProfile());
    }
}
