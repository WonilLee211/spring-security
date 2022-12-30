package com.sp.fc.web.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/greeting")
    public OAuth2User greeting(@AuthenticationPrincipal OAuth2User user){
    // 구글에서 수많은 정보를 전닿해 줌
        return user;
    }
}
