package com.example.project.user.model.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {
    private final Map<String, Object> attributes;
    private Map<String, String> properties;
    private Map<String, String> account;


    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        properties = (Map<String, String>) attributes.get("properties");
        account = (Map<String, String>) attributes.get("kakao_account");
    }

    @Override
    public String getProviderId() {
        System.out.println("KakaoUserInfo의 getProvider");
        System.out.println("providerId : " + attributes.get("id").toString());
        System.out.println("============================================================================");
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return account.get("email");
    }

    @Override
    public String getName() {
        return properties.get("nickname");
    }

    @Override
    public String getProfileImg() {
        //다시 생각해봐야할 수 있음
        return properties.get("profile_image");
    }
}
