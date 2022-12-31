package com.sp.fc.web.Config;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


// OAuth2 사이트를 통해서 유저정보를 가져오게 되면 여기를 거침
@Service
public class SpOAuth2UserService extends DefaultOAuth2UserService {

    // 유저정보가 오면 OAuth2유저를 로딩하는 함수
    // 카카오, 페이스북은 OAuth2유저 스펙을 따름
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return super.loadUser(userRequest);
    }
}
