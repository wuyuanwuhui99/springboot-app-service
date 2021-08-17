package com.player.movie.controller;

import com.player.common.entity.PasswordEntity;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.HttpUtils;
import com.player.common.utils.OperationType;
import com.player.movie.entity.MovieEntity;
import com.player.movie.service.IMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/service")
@Api(value = "抖音查询和记录的接口", description = "查询抖音列表、播放记录接口")
@RestController
public class MovieController {
    @Autowired
    private IMovieService movieService;

    @OperLog(message = "更新用户信息", operation = OperationType.UPDATE)
    @ApiOperation("更新用户信息")
    @PutMapping("/movie-getway/updateUser")
    public ResultEntity updateUser(@RequestBody UserEntity userEntity,@RequestHeader("Authorization") String token) {
        return movieService.updateUser(userEntity,token);
    }

    @ApiOperation("修改密码")
    @PutMapping("/movie-getway/updatePassword")
    public ResultEntity updatePassword(@RequestBody PasswordEntity passwordEntity, @RequestHeader("Authorization") String token) {
        return movieService.updatePassword(passwordEntity,token);
    }

    @OperLog(message = "获取分类信息", operation = OperationType.QUERY)
    @ApiOperation("获取分类信息")
    @GetMapping("/movie/findClassify")
    public ResultEntity findClassify(HttpServletRequest request) {
        String path = request.getRequestURI();
        return movieService.findClassify(path);
    }

    @OperLog(message = "按照类型获取推荐影片", operation = OperationType.QUERY)
    @ApiOperation("按照类型获取推荐影片")
    @GetMapping("/movie/getKeyWord")
    public ResultEntity getKeyWord(
            @RequestParam("classify") String classify,
            HttpServletRequest request
    ) {
        return movieService.getKeyWord(classify, HttpUtils.getPath(request));
    }

    @OperLog(message = "登录校验", operation = OperationType.LOGIN)
    @ApiOperation("登录校验")
    @PostMapping("/movie/login")
    public ResultEntity login(@RequestBody UserEntity userEntity) {
        return movieService.login(userEntity);
    }

    @OperLog(message = "查询用户信息", operation = OperationType.QUERY)
    @ApiOperation("查询用户信息")
    @GetMapping("/movie/getUserData")
    public ResultEntity getUserData(@RequestHeader("Authorization") String token) {
        return movieService.getUserData(token);
    }

    @OperLog(message = "查询当前用户的使用天数，关注数，观看记录数，浏览记录数", operation = OperationType.QUERY)
    @ApiOperation("查询当前用户的使用天数，关注数，观看记录数，浏览记录数")
    @GetMapping("/movie-getway/getUserMsg")
    public ResultEntity getUserMsg(@RequestHeader("Authorization") String token) {
        return movieService.getUserMsg(token);
    }

    @OperLog(message = "按classify大类查询所有catory小类", operation = OperationType.QUERY)
    @ApiOperation("按classify大类查询所有catory小类,请求地址：/service/movie/getAllCategoryByClassify")
    @GetMapping("/movie/getAllCategoryByClassify")
    public ResultEntity getAllCategoryByClassify(
            @RequestParam("classify") String classsify,
            HttpServletRequest request
    ) {
        return movieService.getAllCategoryByClassify(classsify,HttpUtils.getPath(request));
    }

    @OperLog(message = "按页面获取要展示的category小类", operation = OperationType.QUERY)
    @ApiOperation("按页面获取要展示的category小类")
    @GetMapping("/movie/getAllCategoryListByPageName")
    public ResultEntity getAllCategoryListByPageName(
            @RequestParam("pageName") String pageName,
            HttpServletRequest request
    ) {
        return movieService.getAllCategoryListByPageName(pageName,HttpUtils.getPath(request));
    }

    @OperLog(message = "获取大类中的小类", operation = OperationType.QUERY)
    @ApiOperation("获取大类中的小类,请求地地址：/service/movie/getAllCategoryListByPageName")
    @GetMapping("/movie/getCategoryList")
    public ResultEntity getCategoryList(
            @RequestParam("classify") String classify,
            @RequestParam("category") String category,
            HttpServletRequest request
    ) {
        return movieService.getCategoryList(classify, category,HttpUtils.getPath(request));
    }

    @OperLog(message = "搜索", operation = OperationType.QUERY)
    @ApiOperation("搜索,请求地地址：/service/movie/search")
    @GetMapping(value = "/movie/search")
    public ResultEntity search(
            @RequestParam(required = false, value="classify") String classify,
            @RequestParam(required = false, value="category") String category,
            @RequestParam(required = false, value="label") String label,
            @RequestParam(required = false, value="star") String star,
            @RequestParam(required = false, value="director") String director,
            @RequestParam(required = false, value="keyword") String keyword,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize,
            HttpServletRequest request
    ) {
        return movieService.search(classify, category, label,star,director,keyword,pageNum,pageSize,HttpUtils.getPath(request));
    }

    @OperLog(message = "注册", operation = OperationType.QUERY)
    @ApiOperation("注册,请求地地址：/service/movie/register")
    @PutMapping("/movie/register")
    public ResultEntity register(@RequestBody UserEntity userEntity) {
        return movieService.register(userEntity);
    }

    @OperLog(message = "查询用户是否存在", operation = OperationType.QUERY)
    @ApiOperation("查询用户是否存在,请求地地址：/service/movie/getUserById")
    @GetMapping("/movie/getUserById")
    public ResultEntity getUserById(@RequestParam("userId")String userId) {
        return movieService.getUserById(userId);
    }

    @OperLog(message = "获取演员列表", operation = OperationType.QUERY)
    @ApiOperation("获取演员列表：/service/movie/getStar")
    @GetMapping("/movie/getStar")
    public ResultEntity getStar(
            @RequestParam("movieId") String movieId,
            HttpServletRequest request
    ) {
        return movieService.getStar(movieId,HttpUtils.getPath(request));
    }

    @OperLog(message = "获取播放列表", operation = OperationType.QUERY)
    @ApiOperation("获取演员列表：/service/movie/getMovieUrl")
    @GetMapping("/movie/getMovieUrl")
    public ResultEntity getMovieUrl(
            @RequestParam("movieId") String movieId,
            HttpServletRequest request
    ) {
        return movieService.getMovieUrl(movieId,HttpUtils.getPath(request));
    }

    @OperLog(message = "获取观看记录", operation = OperationType.QUERY)
    @ApiOperation("获取观看记录,请求地地址：/service/movie/getPlayRecord")
    @GetMapping("/movie-getway/getPlayRecord")
    public ResultEntity getPlayRecord(@RequestHeader("Authorization") String token) {
        return movieService.getPlayRecord(token);
    }

    @OperLog(message = "保存观看记录", operation = OperationType.ADD)
    @ApiOperation("获取播放记录,请求地地址：/service/movie-getway/savePlayRecord")
    @PostMapping("/movie-getway/savePlayRecord")
    public ResultEntity savePlayRecord(@RequestBody MovieEntity movieEntity,@RequestHeader("Authorization") String token) {
        return movieService.savePlayRecord(movieEntity,token);
    }

    @OperLog(message = "获取收藏记录", operation = OperationType.QUERY)
    @ApiOperation("获取观看记录,请求地地址：/service/movie-getway/getFavoriteList")
    @GetMapping("/movie-getway/getFavoriteList")
    public ResultEntity getFavorite(
            @RequestHeader("Authorization") String token,
            @RequestParam("pageNum")int pageNum,
            @RequestParam("pageSize")int pageSize) {
        return movieService.getFavoriteList(token,pageNum,pageSize);
    }

    @OperLog(message = "保存收藏记录", operation = OperationType.ADD)
    @ApiOperation("保存收藏记录,请求地地址：/service/movie-getway/saveFavorite")
    @PostMapping("/movie-getway/saveFavorite")
    public ResultEntity saveFavorite(@RequestBody MovieEntity movieEntity,@RequestHeader("Authorization") String token) {
        return movieService.saveFavorite(movieEntity,token);
    }

    @OperLog(message = "删除收藏", operation = OperationType.DELETE)
    @ApiOperation("删除收藏,请求地地址：/service/movie-getway/deleteFavorite")
    @DeleteMapping("/movie-getway/deleteFavorite")
    public ResultEntity deleteFavorite(@RequestParam("movieId") String movieId,@RequestHeader("Authorization") String token) {
        return movieService.deleteFavorite(movieId,token);
    }

    @OperLog(message = "查询是否已经收藏", operation = OperationType.QUERY)
    @ApiOperation("查询是否已经收藏,请求地地址：/service/movie-getway/isFavorite")
    @GetMapping("/movie-getway/isFavorite")
    public ResultEntity isFavorite(@RequestParam("movieId") String movieId,@RequestHeader("Authorization") String token) {
        return movieService.isFavorite(movieId,token);
    }

    @OperLog(message = "获取猜你想看的电影", operation = OperationType.QUERY)
    @ApiOperation("获取猜你想看的电影,请求地地址：/service/movie/getYourLikes")
    @GetMapping("/movie/getYourLikes")
    public ResultEntity getYourLikes(@RequestParam("labels") String labels,HttpServletRequest request) {
        return movieService.getYourLikes(labels,HttpUtils.getPath(request));
    }

    @OperLog(message = "获取推荐的电影", operation = OperationType.QUERY)
    @ApiOperation("获取推荐的电影,请求地地址：/service/movie-getway/getRecommend")
    @GetMapping("/movie/getRecommend")
    public ResultEntity getRecommend(@RequestParam("classify") String classify, HttpServletRequest request) {
        return movieService.getRecommend(classify, HttpUtils.getPath(request));
    }
}
