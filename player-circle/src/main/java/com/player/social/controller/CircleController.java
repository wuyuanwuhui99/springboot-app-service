package com.player.social.controller;

import com.player.social.entity.SayEntity;
import com.player.social.service.ICircleService;
import com.player.common.entity.ResultEntity;
import com.player.common.utils.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/service")
@Api(value = "电影圈", description = "新增，查询，修改，删除电影圈")
@RestController
public class CircleController {
    @Autowired
    private ICircleService circleService;

    @ApiOperation("获取电影圈列表")
    @GetMapping("/circle/getCircleArticleList")
    public ResultEntity getCircleArticleList(
            @RequestParam("pageSize") int pageSize,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("type") String type,
            @RequestParam("keyword") String keyword,
            @RequestHeader("Authorization") String token
    ) {
        return circleService.getCircleArticleList(pageNum, pageSize, type, keyword, token);
    }

    @ApiOperation("获取用户登录信息")
    @GetMapping("/circle/getUserData")
    public ResultEntity getUserData(@RequestHeader(required = false, value = "Authorization") String token) {
        return circleService.getUserData(token);
    }

    @ApiOperation("获取文章的评论数量，浏览数量，收藏数量")
    @GetMapping("/circle/getCircleArticleCount")
    public ResultEntity getCircleArticleCount(@RequestParam("id") int id) {
        return circleService.getCircleArticleCount(id);
    }

    @ApiOperation("获取热门影评")
    @GetMapping("/circle/getHotCommentMovie")
    public ResultEntity getHotCommentMovie( HttpServletRequest request) {
        return circleService.getHotCommentMovie(HttpUtils.getPath(request));
    }

    @ApiOperation("获取最近更新的影片")
    @GetMapping("/circle/getLastModifyMovie")
    public ResultEntity getLastModifyMovie( HttpServletRequest request) {
        return circleService.getLastModifyMovie(HttpUtils.getPath(request));
    }

    @ApiOperation("保存图片和文字")
    @PostMapping("/circle-getway/saveSay")
    public ResultEntity saveSay(@RequestBody SayEntity sayEntity,@RequestHeader("Authorization") String token) {
        return circleService.saveSay(sayEntity,token);
    }
}
