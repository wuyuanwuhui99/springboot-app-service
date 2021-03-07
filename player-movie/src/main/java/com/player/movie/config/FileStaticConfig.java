package com.player.movie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class FileStaticConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /**
         *  /source/xxx   指文件的访问方式  如：localhost:8080/source/abc.wav
         *  file:d/voice/  指静态文件存放在服务器上的位置
         */
        registry.addResourceHandler("//images/avater/user/**").addResourceLocations("file:" + "G:\\node\\music\\public\\images\\avater\\user");
    }
}
