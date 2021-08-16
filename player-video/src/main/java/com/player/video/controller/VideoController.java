package com.player.video.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.utils.HttpUtils;
import com.player.video.service.IVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/service")
@Api(value = "抖音查询和记录的接口", description = "查询抖音列表、播放记录接口")
@RestController
public class VideoController {
    @Autowired
    private IVideoService videoService;

    @ApiOperation("获取用户登录信息")
    @GetMapping("/video/getUserData")
    public ResultEntity getUserData(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return videoService.getUserData(token);
    }

    @ApiOperation("获取视频分类信息")
    @GetMapping("/video-getway/getFavoriteChannels")
    public ResultEntity getFavoriteChannels(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return videoService.getFavoriteChannels(token);
    }

    @ApiOperation("获取视频分类信息")
    @GetMapping("/video-getway/getFavoriteList")
    public ResultEntity getFavoriteList(
            @RequestHeader("Authorization") String token,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ) {
        return videoService.getFavoriteList(token,pageNum,pageSize);
    }

    @ApiOperation("获取视频列表")
    @GetMapping("/video/getVideoList")
    public ResultEntity getVideoList(
            @RequestParam("pageSize") int pageSize,
            @RequestParam("pageNum") int pageNum,
            @RequestParam(required = false, value="star") String star,
            @RequestParam(required = false, value="category") String category,
            @RequestParam(required = false, value="type") String type,
            @RequestParam(required = false, value="label") String label,
            @RequestParam(required = false, value="userId") String userId,
            @RequestParam(required = false, value="keyword") String keyword,
            HttpServletRequest request
    ) {
        String path = HttpUtils.getPath(request);
        return videoService.getVideoList(pageNum,pageSize,star,category,type,label,userId,keyword,path);
    }

    @ApiOperation("获取视频分类信息")
    @GetMapping("/video-getway/isFavorite")
    public ResultEntity isFavorite(@RequestHeader("Authorization") String token,@RequestParam("id") int id) {
        return videoService.isFavorite(token,id);
    }

    @ApiOperation("获取视频分类信息")
    @PostMapping("/video-getway/insertFavorite")
    public ResultEntity insertFavorite(@RequestHeader("Authorization") String token,@RequestParam("videoId") int videoId) {
        return videoService.insertFavorite(token,videoId);
    }

    @ApiOperation("获取视频分类信息")
    @DeleteMapping("/video-getway/deleteFavorite")
    public ResultEntity deleteFavorite(@RequestHeader("Authorization") String token,@RequestParam("videoId") int videoId) {
        return videoService.deleteFavorite(token,videoId);
    }
}
