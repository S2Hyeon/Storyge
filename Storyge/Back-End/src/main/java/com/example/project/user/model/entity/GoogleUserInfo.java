package com.example.project.user.model.entity;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{
    private Map<String, Object> attributes; // oauth2user.getAttributes
    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    //추후 nickname으로 저장될 거임
    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getProfileImg() {
        return (String) attributes.get("picture");
    }
}