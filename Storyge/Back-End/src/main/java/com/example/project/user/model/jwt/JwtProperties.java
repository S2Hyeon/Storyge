package com.example.project.user.model.jwt;

public interface JwtProperties {

    String SECRET = "clfauswha307clfauswhclfauswha307clfauswhclfauswha307clfauswh";

    //access token 유효기간 30일
    long ACCESS_TOKEN_TIME = 30 * 24 * 60 * 60 * 1000L;

    // refresh token 유효기간 30일
    long REFRESH_TOKEN_TIME = 30 * 24 * 60 * 60 * 1000L;
    String AUTHORITIES_KEY = "auth";
    String TOKEN_PREFIX = "Bearer ";
    String TOKEN_HEADER = "Authorization";
}
