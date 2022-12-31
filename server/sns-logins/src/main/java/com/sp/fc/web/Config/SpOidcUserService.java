package com.sp.fc.web.Config;


import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;


// Oidc 사이트를 통해서 유저정보를 가져오게 되면 여기를 거침
// 해당 정보를 우리 서비스에서 등록하거나 변환할 때 여기서 사용할 수 있음
@Service
public class SpOidcUserService extends OidcUserService {


    // 구글이 Oicd
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {


        return super.loadUser(userRequest);
    }
}
