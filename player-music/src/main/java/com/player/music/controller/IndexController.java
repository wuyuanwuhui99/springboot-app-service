package com.player.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    ResourceLoader loader;

    @Value("${static.file-path}")
    private String root;
    
     @GetMapping("/")
    public String home() {
        return "redirect:music";
    }

    @GetMapping("/music")
    public String index() {
        return "forward:index.html";
    }
}
