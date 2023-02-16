package com.example.project.user.model.oauth;

import com.example.project.diary.model.entity.DiaryCount;
import com.example.project.diary.model.repository.DiaryCountRepository;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.oauth.provider.GoogleUserInfo;
import com.example.project.user.model.oauth.provider.KakaoUserInfo;
import com.example.project.user.model.oauth.provider.NaverUserInfo;
import com.example.project.user.model.oauth.provider.OAuth2UserInfo;
import com.example.project.user.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
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
    private final DiaryCountRepository diaryCountRepository;

    public CustomOAuth2UserService(UserRepository userRepository, DiaryCountRepository diaryCountRepository) {
        this.userRepository = userRepository;
        this.diaryCountRepository = diaryCountRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return process(userRequest, oAuth2User);
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo userInfo = null;

        //구글로 가입시 user정보 저장
        if (Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "google"))
            userInfo = new GoogleUserInfo(oAuth2User.getAttributes());

            //카카오로 가입시 user정보 저장
        else if (Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "kakao"))
            userInfo = new KakaoUserInfo(oAuth2User.getAttributes());

            //네이버로 가입시 user정보 저장
        else if (Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "naver"))
            userInfo = new NaverUserInfo(oAuth2User.getAttributes());

        //유저 이름은 해당 소셜이름 + 소셜에서 보내준 고유 번호로 저장
        String name = userInfo.getProvider() + '_' + userInfo.getProviderId();


        // 이 유저가 가입이 되어있는지 확인
        Optional<User> userOptional = userRepository.findByName(name);

        User user;
        //만약 가입이 안되어 있다면 저장
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

            // 일기 작성 제한 테이블에도 생성해주기!
            DiaryCount diaryCount = DiaryCount.builder()
                    .userId(user.getUserId())
                    .build();

            diaryCountRepository.save(diaryCount);
        } else {
            user = userOptional.get();
        }

        return new UserDetailCustom(user, oAuth2User.getAttributes());
    }
}
