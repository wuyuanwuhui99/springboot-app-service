package com.player.playermusic.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;

@RestController
public class FileStaticController {
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
