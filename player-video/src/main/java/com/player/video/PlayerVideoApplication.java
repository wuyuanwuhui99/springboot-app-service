package com.player.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.player.video")
@MapperScan("com.player.video.mapper")
public class PlayerVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerVideoApplication.class, args);
    }

}