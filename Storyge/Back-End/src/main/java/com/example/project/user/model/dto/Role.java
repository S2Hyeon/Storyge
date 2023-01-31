package com.example.project.user.model.dto;


public enum Role {

    USER("ROLE_USER");

    private final String key;

    Role(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }
}
