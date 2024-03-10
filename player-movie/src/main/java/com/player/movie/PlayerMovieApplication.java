package com.player.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.player.movie")
@MapperScan("com.player.movie.mapper")
public class PlayerMovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlayerMovieApplication.class, args);
    }
}
