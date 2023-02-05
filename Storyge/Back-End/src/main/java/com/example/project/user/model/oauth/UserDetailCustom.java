package com.example.project.user.model.oauth;

import com.example.project.user.model.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class UserDetailCustom implements OAuth2User {

    private final User user;
    private Map<String, Object> attributes;

    public UserDetailCustom(User user){
        this.user = user;
    }
    public UserDetailCustom(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities");
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(user::getEmail);
        return collection;
    }

    @Override
    public String getName() {
        return user.getNickname();
    }
}
