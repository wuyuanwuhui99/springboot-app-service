package com.player.toutiao.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;
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
    @GetMapping("/toutiao/findArticleList")
    public ResultEntity findArticleList(
            @RequestParam("pageSize") int pageSize,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("type") String type,
            @RequestParam("keyword") String keyword
    ) {
        return toutiaoService.findArticleList(pageNum,pageSize,type,keyword);
    }

    @OperLog(message = "查询文章详情", operation = OperationType.QUERY)
    @ApiOperation("查询文章详情")
    @GetMapping("/toutiao/{id}")
    public ResultEntity findArticleDetail(@PathVariable("id") int id,HttpServletRequest request) {
        String path = HttpUtils.getPath(request);
        return toutiaoService.findArticleDetail(id,path);
    }

    @OperLog(message = "查询用户收藏的频道", operation = OperationType.QUERY)
    @ApiOperation("查询用户收藏的频道")
    @GetMapping("/toutiao/findFavoriteChannels-getway")
    public ResultEntity findFavoriteChannels(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return toutiaoService.findFavoriteChannels(token);
    }

    @ApiOperation("查询所有频道")
    @GetMapping("/toutiao/findAllChannels")
    public ResultEntity findAllChannels() {
        return toutiaoService.findAllChannels();
    }

    @ApiOperation("获取用户登录信息")
    @GetMapping("/toutiao/getUserData")
    public ResultEntity getUserData(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return toutiaoService.getUserData(token);
    }

    @ApiOperation("按照用户id查询用户的文章")
    @GetMapping("/toutiao/findArticleByUserId")
    public ResultEntity findArticleByUserId(
            @RequestParam("userId") String userId,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("keyword") String keyword
    ) {
        return toutiaoService.findArticleByUserId(userId,pageNum,pageSize,keyword);
    }
}
