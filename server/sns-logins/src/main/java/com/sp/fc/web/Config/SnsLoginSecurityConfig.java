package com.sp.fc.web.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SnsLoginSecurityConfig extends WebSecurityConfigurerAdapter {


//    private SpOAuth2UserService oAuth2UserService; // 구글이 제공하는 user 서비스

//    private SpOidcUserService oidcUserService; // 구글이 제공하는 user 서비스

    @Autowired
    private SpOAuth2SuccessHandler successHandler;

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // oauth2를 이용한 로그인을 하겟다.
                .oauth2Login(oauth2 ->oauth2
//                        .userInfoEndpoint(
//                        userInfo -> userInfo.userService(oAuth2UserService)
//                                .oidcUserService(oidcUserService)
//
//                        )// 로그인 성공했을 때 수행할 로직
                                .successHandler(successHandler)
                );
    }
}
