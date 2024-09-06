package com.luan.siteveso.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private OAuth2User oauth2User;
    private String username;

    public CustomOAuth2User(OAuth2User oauth2User, String username) {
        this.oauth2User = oauth2User;
        this.username = username;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getName();
    }

    // Phương thức để thiết lập giá trị username
    public void setUsername(String username) {
        this.username = username;
    }

    // Lấy giá trị username
    public String getUsername() {
        return username;
    }
}
