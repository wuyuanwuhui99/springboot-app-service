package com.player.ebook.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.OperationType;
import com.player.ebook.service.IBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/service/ebook")
@Api(value = "电子书查询", description = "电子书查询")
public class BookController {
    @Autowired
    private IBookService bookService;

    /**
     * @author: wuwenqiang
     * @description: 查询用户数据
     * @date: 2021-01-31 12:33
     */
    @OperLog(message = "查询用户信息", operation = OperationType.QUERY)
    @ApiOperation("查询用户信息")
    @GetMapping("/getUserData")
    public ResultEntity getUserData(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return bookService.getUserData(token);
    }

    /**
     * @author: wuwenqiang
     * @description: 查询所有分类信息
     * @date: 2021-02-1 21:27
     */
    @OperLog(message = "查询所有分类信息", operation = OperationType.QUERY)
    @ApiOperation("查询所有分类信息")
    @GetMapping("/findAllByClassifyGroup")
    public ResultEntity findAllByClassifyGroup(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return bookService.findAllByClassifyGroup(token);
    }

    @OperLog(message = "查询电子书列表", operation = OperationType.QUERY)
    @ApiOperation("查询电子书列表")
    @GetMapping("/findBookList")
    ResultEntity findBookList(
            @RequestParam(required = true,value = "pageSize") Integer pageSize,
            @RequestParam(required = true,value = "pageNum") Integer pageNum,
            @RequestParam(required = false,value = "classify") String classify,
            @RequestParam(required = false,value = "category") String category,
            @RequestParam(required = false,value = "keyword") String keyword,
            HttpServletRequest request
    ){
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String path = requestURI + "?" + queryString;
        return bookService.findBookList(pageSize,pageNum,classify,category,keyword,path);
    }

    /**
     * @author: wuwenqiang
     * @description: 查询轮播图
     * @date: 2021-02-02 20:43
     */
    @OperLog(message = "查询轮播图", operation = OperationType.QUERY)
    @ApiOperation("查询轮播图")
    @GetMapping("/getBanner")
    public ResultEntity getBanner(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return bookService.getBanner(token);
    }
}
