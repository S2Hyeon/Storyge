package com.example.project.user.model.oauth.provider;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {
    private final Map<String, Object> attributes;

    private Map<String, String> response;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        response = (Map<String, String>) attributes.get("response");
    }

    @Override
    public String getProviderId() {
        System.out.println("naver의 getProviderId() : " + response.get("id"));
        return response.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return response.get("email").toString();
    }

    @Override
    public String getName() {
        return response.get("name").toString();
    }

    @Override
    public String getProfileImg() {
        return response.get("profile_image").toString();
    }
}
