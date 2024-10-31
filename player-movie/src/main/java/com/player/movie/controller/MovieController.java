package com.player.movie.controller;

import com.player.common.entity.ResultEntity;
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
    @ApiOperation("获取大类中的小类,请求地地址：/service/movie/getCategoryList")
    @GetMapping("/movie/getCategoryList")
    public ResultEntity getCategoryList(
            @RequestParam("classify") String classify,
            @RequestParam("category") String category,
            HttpServletRequest request
    ) {
        return movieService.getCategoryList(classify, category,HttpUtils.getPath(request));
    }

    @OperLog(message = "根据分类获取前20条数据", operation = OperationType.QUERY)
    @ApiOperation("根据分类获取前20条数据,请求地地址：/service/movie/getTopMovieList")
    @GetMapping("/movie/getTopMovieList")
    public ResultEntity getTopMovieList(
            @RequestParam("classify") String classify,
            @RequestParam(value = "category",required = false) String category,
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

    @OperLog(message = "获取演员列表", operation = OperationType.QUERY)
    @ApiOperation("获取演员列表：/service/movie/getStar/{id}")
    @GetMapping("/movie/getStar/{movieId}")
    public ResultEntity getStar(
            @PathVariable("movieId") Long movieId,
            HttpServletRequest request
    ) {
        return movieService.getStar(movieId,HttpUtils.getPath(request));
    }

    @OperLog(message = "获取播放列表", operation = OperationType.QUERY)
    @ApiOperation("获取演员列表：/service/movie/getMovieUrl")
    @GetMapping("/movie/getMovieUrl")
    public ResultEntity getMovieUrl(
            @RequestParam("movieId") Long movieId,
            HttpServletRequest request
    ) {
        return movieService.getMovieUrl(movieId,HttpUtils.getPath(request));
    }

    @OperLog(message = "获取观看记录", operation = OperationType.QUERY)
    @ApiOperation("获取观看记录,请求地地址：/service/movie/getPlayRecord")
    @GetMapping("/movie-getway/getPlayRecord")
    public ResultEntity getPlayRecord(
            @RequestHeader("Authorization") String token,
            @RequestParam("pageNum")int pageNum,
            @RequestParam("pageSize")int pageSize) {
        return movieService.getPlayRecord(token,pageNum,pageSize);
    }

    @OperLog(message = "保存观看记录", operation = OperationType.ADD)
    @ApiOperation("获取播放记录,请求地地址：/service/movie-getway/savePlayRecord")
    @PostMapping("/movie-getway/savePlayRecord")
    public ResultEntity savePlayRecord(@RequestBody MovieEntity movieEntity,@RequestHeader("Authorization") String token) {
        return movieService.savePlayRecord(movieEntity,token);
    }

    @OperLog(message = "获取观看记录", operation = OperationType.QUERY)
    @ApiOperation("获取观看记录,请求地地址：/service/movie-getway/getViewRecord")
    @GetMapping("/movie-getway/getViewRecord")
    public ResultEntity getViewRecord(
            @RequestHeader("Authorization") String token,
            @RequestParam("pageNum")int pageNum,
            @RequestParam("pageSize")int pageSize) {
        return movieService.getViewRecord(token,pageNum,pageSize);
    }

    @OperLog(message = "保存观看记录", operation = OperationType.ADD)
    @ApiOperation("获取播放记录,请求地地址：/service/movie-getway/saveViewRecord")
    @PostMapping("/movie-getway/saveViewRecord")
    public ResultEntity saveViewRecord(@RequestBody MovieEntity movieEntity,@RequestHeader("Authorization") String token) {
        return movieService.saveViewRecord(movieEntity,token);
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
    @PostMapping("/movie-getway/saveFavorite/{movieId}")
    public ResultEntity saveFavorite(@PathVariable("movieId") int movieId,@RequestHeader("Authorization") String token) {
        return movieService.saveFavorite(movieId,token);
    }

    @OperLog(message = "删除收藏", operation = OperationType.DELETE)
    @ApiOperation("删除收藏,请求地地址：/service/movie-getway/deleteFavorite")
    @DeleteMapping("/movie-getway/deleteFavorite/{movieId}")
    public ResultEntity deleteFavorite(@PathVariable("movieId") int movieId,@RequestHeader("Authorization") String token) {
        return movieService.deleteFavorite(movieId,token);
    }

    @OperLog(message = "查询是否已经收藏", operation = OperationType.QUERY)
    @ApiOperation("查询是否已经收藏,请求地地址：/service/movie-getway/isFavorite")
    @GetMapping("/movie-getway/isFavorite")
    public ResultEntity isFavorite(@RequestParam("movieId") Long movieId,@RequestHeader("Authorization") String token) {
        return movieService.isFavorite(movieId,token);
    }

    @OperLog(message = "获取猜你想看的电影", operation = OperationType.QUERY)
    @ApiOperation("获取猜你想看的电影,请求地地址：/service/movie/getYourLikes")
    @GetMapping("/movie/getYourLikes")
    public ResultEntity getYourLikes(
            @RequestParam("labels") String labels,
            @RequestParam("classify") String classify,
            HttpServletRequest request
    ) {
        return movieService.getYourLikes(labels,classify,HttpUtils.getPath(request));
    }

    @OperLog(message = "获取推荐的电影", operation = OperationType.QUERY)
    @ApiOperation("获取推荐的电影,请求地地址：/service/movie/getRecommend")
    @GetMapping("/movie/getRecommend")
    public ResultEntity getRecommend(@RequestParam("classify") String classify, HttpServletRequest request) {
        return movieService.getRecommend(classify, HttpUtils.getPath(request));
    }

    @ApiOperation("获取电影详情")
    @GetMapping("/movie/getMovieDetail/{movieId}")
    public ResultEntity getMovieDetail(@PathVariable("movieId") int movieId) {
        return movieService.getMovieDetail(movieId);
    }

    @OperLog(message = "获取类型相似的电影", operation = OperationType.QUERY)
    @ApiOperation("获取类型相似的电影,请求地地址：/service/movie/getMovieListByType")
    @GetMapping("/movie/getMovieListByType")
    public ResultEntity getMovieListByType(
            @RequestParam("types") String types,
            @RequestParam("classify") String classify,
            HttpServletRequest request
    ) {
        return movieService.getMovieListByType(types,classify,HttpUtils.getPath(request));
    }

    @OperLog(message = "获取搜索历史", operation = OperationType.QUERY)
    @ApiOperation("获取搜索历史,请求地地址：/service/movie-getway/getSearchHistory")
    @GetMapping("/movie-getway/getSearchHistory")
    public ResultEntity getSearchHistory(
            @RequestHeader("Authorization") String token,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize")int pageSize
    ) {
        return movieService.getSearchHistory(token,pageNum,pageSize);
    }
}
