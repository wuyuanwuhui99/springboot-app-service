package com.player.toutiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.player.toutiao")
@MapperScan("com.player.toutiao.mapper")
public class PlayerToutiaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerToutiaoApplication.class, args);
    }

}
