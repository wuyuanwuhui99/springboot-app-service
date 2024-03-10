package com.player.social;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.player.social")
@MapperScan("com.player.social.mapper")
public class PlayerSocialApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlayerSocialApplication.class, args);
    }

}
