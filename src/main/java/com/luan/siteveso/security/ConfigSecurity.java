package com.luan.siteveso.security;

import com.luan.siteveso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class ConfigSecurity {
    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config->
                config
                        .requestMatchers(HttpMethod.POST,"/ad/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/ad/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/us/**","/dang-nhap").permitAll()
                        .requestMatchers(HttpMethod.POST,"/us/**","/dang-ky-moi","/dang-nhap").permitAll()
                        .requestMatchers(HttpMethod.GET,"/thanh-cong").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/css/**","/js/**").permitAll() // khai bao css, js
                        .anyRequest().authenticated()

        ).formLogin(form->
                form
                        .loginPage("/dang-nhap")
                        .loginProcessingUrl("/authenticateTheUser") // url mac dinh cua spring security
                        .defaultSuccessUrl("/thanh-cong",true) // xac thuc thanh cong duong dan
                        .permitAll()

        ).oauth2Login(
                oauth2->oauth2
                        .loginPage("/dang-nhap")
                        .authorizationEndpoint(authorization ->
                                authorization
                                .baseUri("/dang-nhap/oauth2/authorization"))
                        .successHandler(oAuth2AuthenticationSuccessHandler)

                )
                .logout(logout-> logout.permitAll());
        http.csrf(csrf-> csrf.disable());

        return http.build();
    }
}
