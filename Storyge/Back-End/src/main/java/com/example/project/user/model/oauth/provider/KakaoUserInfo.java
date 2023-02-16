package com.example.project.user.model.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {
    private final Map<String, Object> attributes;
    private final Map<?, ?> properties;
    private final Map<?, ?> account;


    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        properties = (Map<?, ?>) attributes.get("properties");
        account = (Map<?, ?>) attributes.get("kakao_account");
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return account.get("email").toString();
    }

    @Override
    public String getName() {
        return properties.get("nickname").toString();
    }

    @Override
    public String getProfileImg() {
        //다시 생각해봐야할 수 있음
        return properties.get("profile_image").toString();
    }
}
