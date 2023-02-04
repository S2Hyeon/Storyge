package com.example.project.user.model.oauth;

import com.example.project.user.model.entity.User;
import com.example.project.user.model.oauth.provider.GoogleUserInfo;
import com.example.project.user.model.oauth.provider.KakaoUserInfo;
import com.example.project.user.model.oauth.provider.NaverUserInfo;
import com.example.project.user.model.oauth.provider.OAuth2UserInfo;
import com.example.project.user.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomOAuth2UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("서비스 옴?");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return process(userRequest, oAuth2User);
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo userInfo = null;

        if (Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "google")) {
            userInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "kakao")) {
            userInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else if (Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "naver")) {
            userInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }

        String name = userInfo.getProvider() + '_' + userInfo.getProviderId();
        System.out.println(name);
        Optional<User> userOptional = userRepository.findByName(name);
//        String email = userInfo.getProvider() + '_' + userInfo.getProviderId() + "@strogy.com";
//        Optional<User> userOptional = userRepository.findByEmail(email);

        User user;
        if (userOptional.isEmpty()) {
            user = User.builder()
                    .email(userInfo.getEmail())
                    .name(name)
                    .role("ROLE_USER")
                    .nickname(userInfo.getName())
                    .profileImg(userInfo.getProfileImg())
                    .provider(userInfo.getProvider())
                    .providerId(userInfo.getProviderId())
                    .build();

            userRepository.save(user);
        } else {
            user = userOptional.get();
        }

        return new UserDetailCustom(user, oAuth2User.getAttributes());
    }
}