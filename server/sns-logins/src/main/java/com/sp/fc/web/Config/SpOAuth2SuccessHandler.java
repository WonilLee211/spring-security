package com.sp.fc.web.Config;

import com.sp.fc.user.domain.SpOAuth2User;
import com.sp.fc.user.domain.SpUser;
import com.sp.fc.user.service.SpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// OAuth2 로그인이 성공했을 때 거치게 될 handler
@Component
public class SpOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SpUserService userService;
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException
    {
        System.out.println("------------------------");
        // 성공 후 Authentication 받으면 그 안에 principal에서 유저정보가 담겨 있을 것
        Object principal = authentication.getPrincipal();
        // OAuth2User가  OidcUser보다 상위 객체이기 때문에 OidcUser부터 검사해야 함
        if(principal instanceof OidcUser){
            /* google
                OidcUser의 정보를 이용하여 SpUser를 만들어야 함
             */
            SpOAuth2User oauth = SpOAuth2User.Provider.google.convert((OidcUser) principal);

            SpUser user = userService.load(oauth);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
            );
        }else if(principal instanceof OAuth2User){
            // naver
            SpOAuth2User oauth = SpOAuth2User.Provider.naver.convert((OidcUser) principal);

            SpUser user = userService.load(oauth);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
            );

        }
        // 보낼 url 지정
        request.getRequestDispatcher("/").forward(request, response);


    }
}
