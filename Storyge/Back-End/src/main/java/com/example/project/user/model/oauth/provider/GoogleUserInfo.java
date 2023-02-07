package com.example.project.user.model.oauth.provider;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        System.out.println("google의 getProviderId() : "+ attributes.get("sub").toString());
        return attributes.get("sub").toString();
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        System.out.println("google의 getEmail() : "+ attributes.get("email").toString());
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        System.out.println("google의 getName() : "+ attributes.get("name").toString());
        return attributes.get("name").toString();
    }

    @Override
    public String getProfileImg() {
        System.out.println("google의 getProfileImg() : "+ attributes.get("picture").toString());
        return attributes.get("picture").toString();
    }
}
