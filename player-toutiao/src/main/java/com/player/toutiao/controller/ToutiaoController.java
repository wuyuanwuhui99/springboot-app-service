package com.player.toutiao.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.HttpUtils;
import com.player.common.utils.OperationType;
import com.player.toutiao.service.IToutiaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/service")
@Api(value = "抖音查询和记录的接口", description = "查询抖音列表、播放记录接口")
@RestController
public class ToutiaoController {
    @Autowired
    private IToutiaoService toutiaoService;

    @OperLog(message = "查询文章列表", operation = OperationType.QUERY)
    @ApiOperation("查询文章列表")
    @GetMapping("/toutiao/getArticleList")
    public ResultEntity getArticleList(
            @RequestParam("pageSize") int pageSize,
    @RequestParam("pageNum") int pageNum,
    @RequestParam(required = false, value="type") String type,
    @RequestParam(required = false, value="channelId") String channelId,
    @RequestParam(required = false, value="authorId") String authorId,
    @RequestParam(required = false, value="keyword") String keyword,
    HttpServletRequest request
    ) {
        String path = HttpUtils.getPath(request);
        return toutiaoService.getArticleList(pageNum,pageSize,type,channelId,authorId,keyword,path);
    }

    @OperLog(message = "查询文章详情", operation = OperationType.QUERY)
    @ApiOperation("查询文章详情")
    @GetMapping("/toutiao/getArticleDetail/{id}")
    public ResultEntity getArticleDetail(@PathVariable("id") int id,@RequestHeader(required = false,value = "Authorization") String token) {
        return toutiaoService.getArticleDetail(id,token);
    }

    @OperLog(message = "查询用户收藏的频道", operation = OperationType.QUERY)
    @ApiOperation("查询用户收藏的频道")
    @GetMapping("/toutiao-getway/getArticleFavoriteChannels")
    public ResultEntity getFavoriteChannels(@RequestHeader("Authorization") String token) {
        return toutiaoService.getFavoriteChannels(token);
    }

    @ApiOperation("查询所有频道")
    @GetMapping("/toutiao/getAllChannels")
    public ResultEntity getAllChannels() {
        return toutiaoService.getAllChannels(null);
    }

    @ApiOperation("获取用户登录信息")
    @GetMapping("/toutiao/getUserData")
    public ResultEntity getUserData(@RequestHeader(required = false,value = "Authorization") String token) {
        return toutiaoService.getUserData(token);
    }

    @ApiOperation("获取用户登录信息")
    @GetMapping("/toutiao-getway/getVideoFavoriteChannels")
    public ResultEntity getVideoCategory(@RequestHeader("Authorization") String token) {
        return toutiaoService.getVideoCategory(token);
    }

    @ApiOperation("获取视频列表")
    @GetMapping("/toutiao/getVideoList")
    public ResultEntity getVideoList(
            @RequestParam("pageSize") int pageSize,
            @RequestParam("pageNum") int pageNum,
            @RequestParam(required = false, value="star") String star,
            @RequestParam(required = false, value="category") String category,
            @RequestParam(required = false, value="type") String type,
            @RequestParam(required = false, value="label") String label,
            @RequestParam(required = false, value="authorId") String authorId,
            @RequestParam(required = false, value="keyword") String keyword,
            @RequestHeader(required = false,value = "Authorization") String token
    ) {
        return toutiaoService.getVideoList(pageSize,pageNum,star,category,type,label,authorId,keyword,token);
    }

    @ApiOperation("获取视频列表")
    @GetMapping("/toutiao/getMovieList")
    public ResultEntity getMovieList(
            @RequestParam("pageSize") int pageSize,
            @RequestParam("pageNum") int pageNum,
            @RequestParam(required = false, value="star") String star,
            @RequestParam(required = false, value="classify") String classify,
            @RequestParam(required = false, value="category") String category,
            @RequestParam(required = false, value="type") String type,
            @RequestParam(required = false, value="label") String label,
            @RequestParam(required = false, value="keyword") String keyword,
            @RequestHeader(required = false,value = "Authorization") String token
    ) {
        return toutiaoService.getMovieList(pageSize,pageNum,star,classify,category,type,label,keyword,token);
    }

    @ApiOperation("获取浏览记录")
    @GetMapping("/toutiao-getway/getArticleRecordList")
    public ResultEntity getArticleRecordList(@RequestHeader("Authorization") String token) {
        return toutiaoService.getArticleRecordList(token);
    }
}
