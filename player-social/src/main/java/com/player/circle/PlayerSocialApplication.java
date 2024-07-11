package com.player.circle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.player.circle")
@MapperScan("com.player.circle.mapper")
public class PlayerSocialApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlayerSocialApplication.class, args);
    }

}
