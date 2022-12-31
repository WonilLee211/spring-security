package com.sp.fc.web.Controller;

import com.sp.fc.user.domain.SpUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public Object greeting(@AuthenticationPrincipal Object user){
        return user;
    }

//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/user")
//    public SpUser greeting(@AuthenticationPrincipal SpUser user){
//        return user;
//    }
}
