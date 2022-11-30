package com.player.circle.controller;

import com.player.circle.service.ICircleService;
import com.player.common.entity.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
