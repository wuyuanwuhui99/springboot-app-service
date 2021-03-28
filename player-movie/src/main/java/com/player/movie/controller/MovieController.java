package com.player.movie.controller;

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

@RequestMapping("/service")
@Api(value = "抖音查询和记录的接口", description = "查询抖音列表、播放记录接口")
@RestController
public class MovieController {
    @Autowired
    private IMovieService movieService;

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
        String userId = userEntity.getUserId();
        String password = userEntity.getPassword();
        return movieService.login(userId, password);
    }

    @OperLog(message = "查询用户信息", operation = OperationType.QUERY)
    @ApiOperation("查询用户信息")
    @GetMapping("/movie/getUserData")
    public ResultEntity getUserData(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return movieService.getUserData(token);
    }

    @OperLog(message = "查询当前用户的使用天数，关注数，观看记录数，浏览记录数", operation = OperationType.QUERY)
    @ApiOperation("查询当前用户的使用天数，关注数，观看记录数，浏览记录数")
    @GetMapping("/movie/getUserMsg")
    public ResultEntity getUserMsg(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
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
    @GetMapping("/movie/search")
    public ResultEntity search(
            @RequestParam("keyword") String keyword,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize,
            HttpServletRequest request
    ) {
        return movieService.search(keyword, pageNum, pageSize,HttpUtils.getPath(request));
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


    @OperLog(message = "获取浏览记录", operation = OperationType.QUERY)
    @ApiOperation("获取播放记录,请求地地址：/service/movie/getViewRecord")
    @GetMapping("/movie-getway/getViewRecord")
    public ResultEntity getViewRecord(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return movieService.getViewRecord(token);
    }

    @OperLog(message = "保存浏览记录", operation = OperationType.ADD)
    @ApiOperation("获取播放记录,请求地地址：/service/movie/saveViewRecord")
    @PostMapping("/movie-getway/saveViewRecord")
    public ResultEntity saveViewRecord(@RequestBody MovieEntity movieEntity,HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return movieService.saveViewRecord(movieEntity,token);
    }

    @OperLog(message = "获取观看记录", operation = OperationType.QUERY)
    @ApiOperation("获取观看记录,请求地地址：/service/movie/getPlayRecord")
    @GetMapping("/movie-getway/getPlayRecord")
    public ResultEntity getPlayRecord(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return movieService.getPlayRecord(token);
    }

    @OperLog(message = "保存观看记录", operation = OperationType.ADD)
    @ApiOperation("获取播放记录,请求地地址：/service/movie/savePlayRecord")
    @PostMapping("/movie-getway/savePlayRecord")
    public ResultEntity savePlayRecord(@RequestBody MovieEntity movieEntity,HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return movieService.savePlayRecord(movieEntity,token);
    }

    @OperLog(message = "获取收藏记录", operation = OperationType.QUERY)
    @ApiOperation("获取观看记录,请求地地址：/service/movie/getFavoriteList")
    @GetMapping("/movie-getway/getFavoriteList")
    public ResultEntity getFavorite(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return movieService.getFavoriteList(token);
    }

    @OperLog(message = "保存收藏记录", operation = OperationType.ADD)
    @ApiOperation("保存收藏记录,请求地地址：/service/movie/saveFavorite")
    @PostMapping("/movie-getway/saveFavorite")
    public ResultEntity saveFavorite(@RequestBody MovieEntity movieEntity,HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return movieService.saveFavorite(movieEntity,token);
    }

    @OperLog(message = "删除收藏", operation = OperationType.DELETE)
    @ApiOperation("保存收藏记录,请求地地址：/service/movie/deleteFavorite")
    @DeleteMapping("/movie-getway/deleteFavorite")
    public ResultEntity deleteFavorite(@RequestParam("movieId") String movieId,HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return movieService.deleteFavorite(movieId,token);
    }

    @OperLog(message = "查询是否已经收藏", operation = OperationType.QUERY)
    @ApiOperation("保存收藏记录,请求地地址：/service/movie/isFavorite")
    @GetMapping("/movie-getway/isFavorite")
    public ResultEntity isFavorite(@RequestParam("movieId") String movieId,HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return movieService.isFavorite(movieId,token);
    }

    @OperLog(message = "获取猜你想看的电影", operation = OperationType.QUERY)
    @ApiOperation("获取猜你想看的电影,请求地地址：/service/movie/getYourLikes")
    @GetMapping("/movie/getYourLikes")
    public ResultEntity getYourLikes(@RequestParam("labels") String labels,HttpServletRequest request) {
        return movieService.getYourLikes(labels,HttpUtils.getPath(request));
    }

    @OperLog(message = "获取推荐的电影", operation = OperationType.QUERY)
    @ApiOperation("获取推荐的电影,请求地地址：/service/movie/getRecommend")
    @GetMapping("/movie/getRecommend")
    public ResultEntity getRecommend(@RequestParam("classify") String classify, HttpServletRequest request) {
        return movieService.getRecommend(classify, HttpUtils.getPath(request));
    }
}
