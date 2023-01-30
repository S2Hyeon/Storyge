package com.example.project.user.model.service.oauth;

import com.example.project.user.model.entity.GoogleUserInfo;
import com.example.project.user.model.entity.OAuth2UserInfo;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final HttpSession session;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //사용자 정보
        OAuth2User oAuth2User = super.loadUser(userRequest);

        //회원가입 진행
        OAuth2UserInfo oAuth2UserInfo = null;
        if(Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "google"))
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
//        else if(Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "naver"))
//            oAuth2User = new NaverUserInfo(oAuth2User.getAttributes());
//        else if(Objects.equals(userRequest.getClientRegistration().getRegistrationId(), "kakao"))
//            oAuth2User = new KakaoUserInfo(oAuth2User.getAttributes())

        Optional<User> user = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        if(user.isEmpty()){
            userRepository.save(User.builder()
                    .email(oAuth2UserInfo.getEmail())
                    .nickname(oAuth2UserInfo.getName())
                    .profileImg(oAuth2UserInfo.getProfileImg())
//                    .refreshToken()
                    .role("ROLE_USER")
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build());
        }else{
            System.out.println("이미 회원가입한 유저, 자동 회원가입이 되어있음");
        }
        return new CustomUserDetailsService(user, oAuth2User.getAttributes());
    }
}
