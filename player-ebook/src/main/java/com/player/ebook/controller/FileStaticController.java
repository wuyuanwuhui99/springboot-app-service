package com.player.ebook.controller;

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

    @GetMapping("/static/**")
    public ResponseEntity<byte[]> getFile(HttpServletRequest request) {
        try {
            String servletPath = request.getServletPath();
            String filePath = root.replace("/","") + servletPath;
            File file = new File(filePath);
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();
            bodyBuilder.contentLength(file.length());
            // 二进制数据流
            bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
            // 在浏览器中打开
            URL url = new URL("file:///" + file);
            bodyBuilder.header("Content-Type", url.openConnection().getContentType());
            String fileName = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length());
            bodyBuilder.header("Content-Disposition", "inline;filename*=UTF-8''" + fileName);
            return bodyBuilder.body(FileUtils.readFileToByteArray(file));
        } catch (Exception e) {
            return null;
        }
    }
}
