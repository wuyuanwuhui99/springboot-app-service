package com.player.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.player.user")
@MapperScan("com.player.user.mapper")
public class PlayerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerUserApplication.class, args);
    }

}
