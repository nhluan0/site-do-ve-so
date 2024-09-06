package com.luan.siteveso.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauthUser = ((OAuth2AuthenticationToken) authentication).getPrincipal();
        String email = oauthUser.getAttribute("name");

        if(email == null){
            email = oauthUser.getAttribute("email");
        }
//        System.out.println(oauthUser);

        // Tạo đối tượng UserDetails
        UserDetails userDetails = new User(email, "", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        // tao doi tuong OAuthUser de set userName cho principal de hien thi tren thymeleaf
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(oauthUser,email);
        // Tạo đối tượng Authentication
        Authentication auth = new OAuth2AuthenticationToken(customOAuth2User, userDetails.getAuthorities(), userDetails.getAuthorities().toString());

        // Lưu thông tin Authentication vào SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Điều hướng người dùng đến trang chính sau khi đăng nhập thành công
        response.sendRedirect("/us/trang-chu");
    }
}
