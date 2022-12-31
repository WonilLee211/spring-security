package com.sp.fc.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import static java.lang.String.format;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sp_oauth2_user")
public class SpOAuth2User {

    @Id
    private String oath2UserId; // naver id, google id

    private Long userId;
    private String name;    // spUser
    private String email;
    private LocalDateTime created;
    private Provider provider;

    public static enum Provider {
        google{
            public SpOAuth2User convert(OAuth2User user){
                return SpOAuth2User.builder()
                        .oath2UserId(format("%s_%s", name(), user.getAttribute("sub")))
                        .provider(google)
                        .email(user.getAttribute("email"))
                        .name(user.getAttribute("user"))
                        .created(LocalDateTime.now())
                        .build();
            }
        },
        naver{
            public SpOAuth2User convert(OAuth2User user){
                Map<String, Object> resp = user.getAttribute("response");

                return SpOAuth2User.builder()
                        .oath2UserId(format("%s_%s", name(), resp.get("id")))
                        .provider(naver)
                        .email("" + resp.get("email"))
                        .name("" + resp.get("name"))
                        .build();
            }
        };

        public abstract SpOAuth2User convert(OAuth2User userInfo);
    }

}
