package com.example.project.user.model.jwt;

public interface JwtProperties {

    String SECRET = "clfauswha307clfauswhclfauswha307clfauswhclfauswha307clfauswh";
    long ACCESS_TOKEN_TIME = 1000L * 60 * 30;
    long REFRESH_TOKEN_TIME = 7 * 24 * 60 * 60 * 1000L;
    String AUTHORITIES_KEY = "auth";
    String TOKEN_PREFIX = "Bearer ";
    String ACCESS_TOKEN = "access-token";
    String TOKEN_HEADER = "Authorization";
}
