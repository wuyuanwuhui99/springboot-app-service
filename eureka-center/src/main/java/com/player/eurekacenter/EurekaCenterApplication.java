package com.player.eurekacenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaCenterApplication.class, args);
    }

}
