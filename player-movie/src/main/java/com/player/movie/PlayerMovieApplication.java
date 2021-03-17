package com.player.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.player.movie")
@MapperScan("com.player.movie.mapper")
public class PlayerMovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlayerMovieApplication.class, args);
    }
}
