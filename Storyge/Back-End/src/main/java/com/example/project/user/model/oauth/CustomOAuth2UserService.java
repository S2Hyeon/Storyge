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
        System.out.println("CustomOAuth2UserService 의 loadUser");
        System.out.println("======================================================================");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return process(userRequest, oAuth2User);
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        System.out.println("CustomOAuth2UserService 의 process");
        System.out.println("======================================================================");
        OAuth2UserInfo userInfo = null;

        if (Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "google")) {
            userInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "kakao")) {
            userInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else if (Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "naver")) {
            userInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }

        String name = userInfo.getProvider() + '_' + userInfo.getProviderId();
        System.out.println("CustomOAuth2UserService process name : " + name);
        Optional<User> userOptional = userRepository.findByName(name);

        User user;
        if (userOptional.isEmpty()) {
            System.out.println("유저가 없으니까 저장하자");
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
            System.out.println("유저가 있으니까 있는 애로 바꿔주자");
            user = userOptional.get();
        }

        return new UserDetailCustom(user, oAuth2User.getAttributes());
    }
}