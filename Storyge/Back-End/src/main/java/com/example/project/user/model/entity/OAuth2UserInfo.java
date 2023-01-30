package com.example.project.user.model.entity;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();

    String getProfileImg();
}
