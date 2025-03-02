package com.player.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.player.music.mapper")
public class PlayerMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerMusicApplication.class, args);
    }
}
