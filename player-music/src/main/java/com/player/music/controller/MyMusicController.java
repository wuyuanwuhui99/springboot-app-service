package com.player.music.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.utils.HttpUtils;
import com.player.music.service.IMyMusicService;
import com.player.music.service.IQQMusicService;
import feign.Param;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController

@RequestMapping("/service")
public class MyMusicController {
    @Autowired
    private IMyMusicService myMusicService;

    @ApiOperation("获取搜索框默认推荐音乐")
    @GetMapping("/myMusic/getKeywordMusic")
    public ResultEntity getKeywordMusic(HttpServletRequest request) {
        return myMusicService.getKeywordMusic(HttpUtils.getPath(request));
    }

    @ApiOperation("获取音乐分类")
    @GetMapping("/myMusic/getMusicClassify")
    public ResultEntity getClassifyMusic(HttpServletRequest request) {
        return myMusicService.getMusicClassify(HttpUtils.getPath(request));
    }

    @ApiOperation("获取推荐音乐列表,isRedis表示是否从redis中获取数据")
    @GetMapping("/myMusic/getMusicListByClassifyId")
    public ResultEntity getMusicListByClassifyId(
            HttpServletRequest request,
            @RequestParam(name = "classifyId",required = true) int classifyId,
            @RequestParam(name = "pageNum",required = true) int pageNum,
            @RequestParam(name = "pageSize",required = true) int pageSize,
            @RequestParam(name = "isRedis",defaultValue = "0",required = false) int isRedis,
            @RequestHeader(required = false, value = "Authorization") String token
    ) {
        return myMusicService.getMusicListByClassifyId(HttpUtils.getPath(request), classifyId, pageNum, pageSize, isRedis != 0, token);
    }

    @ApiOperation("获取歌手")
    @GetMapping("/myMusic/getSingerList")
    public ResultEntity getSingerList(HttpServletRequest request, int pageNum, int pageSize) {
        return myMusicService.getSingerList(HttpUtils.getPath(request), pageNum, pageSize);
    }
}
