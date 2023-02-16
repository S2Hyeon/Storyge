package com.example.project.user.model.oauth.provider;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {
    private final Map<?, ?> attributes;


    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<?, ?>) attributes.get("response");
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

    @Override
    public String getProfileImg() {
        return attributes.get("profile_image").toString();
    }
}
