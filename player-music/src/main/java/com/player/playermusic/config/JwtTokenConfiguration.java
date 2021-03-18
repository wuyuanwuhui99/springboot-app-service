package com.player.playmusic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.player.common.utils.JwtToken;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class JwtTokenConfiguration {
    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration-time}")
    private Long expirationTime;
  
    @Bean
    public JwtToken getJwtToken() {
        return new JwtToken(secret,expirationTime);
    }
}
