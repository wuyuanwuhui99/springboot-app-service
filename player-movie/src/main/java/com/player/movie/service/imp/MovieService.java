package com.player.movie.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.PasswordEntity;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.Common;
import com.player.common.utils.JwtToken;
import com.player.movie.entity.CommentEntity;
import com.player.movie.entity.MovieEntity;
import com.player.movie.mapper.MovieMapper;
import com.player.movie.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MovieService implements IMovieService {
    
    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @author: wuwenqiang
     * @description: 更新用户信息
     * @date: 2020-12-24 22:40
     */
    @Override
    public ResultEntity updateUser(UserEntity userEntity,String token) {
        return restTemplate.exchange(
                Common.putRequestEntity("http://player-user/service/user-getway/updateUser",token,userEntity),
                ResultEntity.class
        ).getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 修改密码
     * @date: 2020-12-24 22:40
     */
    @Override
    public ResultEntity updatePassword(PasswordEntity passwordEntity, String token) {
        return restTemplate.exchange(
                Common.postRequestEntity("http://player-user/service/user-getway/updatePassword",token,passwordEntity),
                ResultEntity.class
        ).getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 查询电影分类
     * @date: 2020-12-24 22:40
     */
    @Override
    public ResultEntity findClassify(String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(movieMapper.findClassify());
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 获取推荐的影片
     * @date: 2020-12-25 00:04
     */
    @Override
    public ResultEntity getKeyWord(String classify,String path) {
        String url = path + "?classify=" + classify;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(movieMapper.getKeyWord(classify));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 登录校验
     * @date: 2020-12-26 10:53
     */
    @Override
    public ResultEntity login(UserEntity userEntity) {
        return restTemplate.exchange(
                Common.postRequestEntity("http://player-user/service/user/login",null,userEntity),
                ResultEntity.class
        ).getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 注册
     * @date: 2021-01-01 23:39
     */
    @Override
    public ResultEntity register(UserEntity userEntity) {
        return restTemplate.exchange(Common.postRequestEntity("http://player-user/service/user/register",null,userEntity),ResultEntity.class).getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 查询单个用户，用于校验用户是否存在
     * @date: 2021-01-01 23:39
     */
    @Override
    public ResultEntity getUserById(String userId) {
        return restTemplate.exchange(
                "http://player-user/service/user/getUserById?userId="+userId,
                HttpMethod.GET,
                new HttpEntity<String>(new HttpHeaders()),
                ResultEntity.class
        ).getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 获取用户数据
     * @date: 2020-12-25 00:04
     */
    @Override
    public ResultEntity getUserData(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        return restTemplate.exchange(
                "http://player-user/service/user/getUserData",
                HttpMethod.GET,
                new HttpEntity<String>(headers),ResultEntity.class
        ).getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 获取用户访问记录
     * @date: 2020-12-21 22:40
     */
    @Override
    public ResultEntity getUserMsg(String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(movieMapper.getUserMsg(userEntity.getUserId()));
    }

    /**
     * @author: wuwenqiang
     * @description: 按classify大类查询所有catory小类
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity getAllCategoryByClassify(String classsify,String path) {
        String url = path + "?classsify=" + classsify;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(movieMapper.getAllCategoryByClassify(classsify));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 按页面获取要展示的category小类
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity getAllCategoryListByPageName(String pageName,String path) {
        String url = path + "?pageName=" + pageName;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity =ResultUtil.success(movieMapper.getAllCategoryListByPageName(pageName));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 获取大类中的小类
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity getCategoryList(String classify, String category,String path) {
        String url = path + "?classify=" + classify + "&category=" + category;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity =  ResultUtil.success(movieMapper.getCategoryList(classify, category));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 搜索
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity search(String classify, String category, String label,String star,String director,String keyword,int pageNum,int pageSize,String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            if(pageSize > 100)pageSize = 100;
            int start = (pageNum - 1) * pageSize;
            ResultEntity resultEntity =  ResultUtil.success(movieMapper.search(classify, category, label,star,director,keyword,start,pageSize));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }


    /**
     * @author: wuwenqiang
     * @description: 播放记录
     * @date: 2020-12-26 10:41
     */
    @Override
    public ResultEntity getStar(Long movieId,String path) {
        String url = path + "?movieId=" + movieId;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity =  ResultUtil.success(movieMapper.getStar(movieId));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 获取播放地址
     * @date: 2020-12-26 10:41
     */
    @Override
    public ResultEntity getMovieUrl(Long movieId,String path) {
        String url = path + "?movieId=" + movieId;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity =  ResultUtil.success(movieMapper.getMovieUrl(movieId));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 获取播放记录
     * @date: 2021-02-28 12:08
     */
    @Override
    public ResultEntity getPlayRecord(String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        ResultEntity resultEntity = ResultUtil.success(movieMapper.getPlayRecord(userEntity.getUserId()));
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @description: 保存浏览记录
     * @date: 2020-12-25 22:29
     */
    @Override
    @Transactional
    public ResultEntity savePlayRecord(MovieEntity movieEntity,String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        Date date = new Date();
        movieEntity.setCreateTime(date);
        movieEntity.setUpdateTime(date);
        movieEntity.setUserId(userEntity.getUserId());
        ResultEntity resultEntity = ResultUtil.success(movieMapper.savePlayRecord(movieEntity));
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @description: 查询收藏列表
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity getFavoriteList(String token,int pageNum,int pageSize) {
        if(pageSize > 100) pageSize = 100;
        int start = (pageNum - 1)*pageSize;
        return ResultUtil.success(movieMapper.getFavoriteList(JwtToken.getUserId(token),start,pageSize));
    }

    /**
     * @author: wuwenqiang
     * @description: 添加收藏
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity saveFavorite(Long movieId, String token) {
        return ResultUtil.success(movieMapper.saveFavorite(movieId,JwtToken.getUserId(token)));
    }

    /**
     * @author: wuwenqiang
     * @description: 删除收藏
     * @date: 2021-03-07 16:10
     */
    @Override
    public ResultEntity deleteFavorite(Long movieId,String token) {
        return ResultUtil.success(movieMapper.deleteFavorite(movieId,JwtToken.getUserId(token)));
    }

    @Override
    public ResultEntity isFavorite(Long movieId,String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(movieMapper.isFavorite(movieId,userEntity.getUserId()));
    }

    @Override
    public ResultEntity getYourLikes(String labels,String classify,String path) {
        String url = path + "?labels=" + labels;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            labels = labels.replaceAll("^/|/$","");
            String[] myLabels = labels.split("/");
            ResultEntity resultEntity = ResultUtil.success(movieMapper.getYourLikes(myLabels,classify));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    @Override
    public ResultEntity getRecommend(String classify,String path) {
        String url = path + "?classify=" + classify;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity = JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(movieMapper.getRecommend(classify));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 添加收藏
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity saveLike(Long movieId, String token) {
        return ResultUtil.success(movieMapper.saveLike(movieId,JwtToken.getUserId(token)));
    }

    /**
     * @author: wuwenqiang
     * @description: 删除收藏
     * @date: 2021-03-07 16:10
     */
    @Override
    public ResultEntity deleteLike(Long movieId,String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(movieMapper.deleteLike(movieId,userEntity.getUserId()));
    }

    @Override
    public ResultEntity isLike(Long movieId,String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(movieMapper.isLike(movieId,userEntity.getUserId()));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取总评论数量
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getCommentCount(int movieId){
        return ResultUtil.success(movieMapper.getCommentCount(movieId));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取一级评论列表
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getTopCommentList(int movieId,int pageNum, int pageSize){
        if(pageSize > 100)pageSize = 100;
        int start = (pageNum - 1)*pageSize;
        return ResultUtil.success(movieMapper.getTopCommentList(movieId,start,pageSize));
    }

    /**
     * @author: wuwenqiang
     * @description: 新增评论
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity insertComment(String token, CommentEntity commentEntity){
        commentEntity.setUserId(JwtToken.getUserId(token));
        movieMapper.insertComment(commentEntity);
        movieMapper.getCommentItem(commentEntity.getId());
        return ResultUtil.success(movieMapper.getCommentItem(commentEntity.getId()));
    }

    /**
     * @author: wuwenqiang
     * @description: 删除评论
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity deleteComment(int id,String userId){
        return ResultUtil.success(movieMapper.deleteComment(id,userId));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取回复列表
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getReplyCommentList(int topId,int pageNum,int pageSize){
        if(pageSize > 100)pageSize = 100;
        int start = (pageNum - 1)*pageSize;
        return ResultUtil.success(movieMapper.getReplyCommentList(topId,start,pageSize));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取历史记录
     * @date: 2021-08-24 21:59
     */
    @Override
    public ResultEntity getRecordList(String token){
        return ResultUtil.success(movieMapper.getRecordList(JwtToken.getUserId(token)));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取电影详情
     * @date: 2021-08-25 22:22
     */
    @Override
    public ResultEntity getMovieDetail(int movieId){
        return ResultUtil.success(movieMapper.getMovieDetail(movieId));
    }


    /**
     * @author: wuwenqiang
     * @description: 按类型获取电影
     * @date: 2021-08-28 11:43
     */
    @Override
    public ResultEntity getMovieListByType(String types,String classify,String path) {
        String url = path + "?classify="+classify+"&types=" + types;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            String[] myTypes = types.split(" ");
            ResultEntity resultEntity = ResultUtil.success(movieMapper.getMovieListByType(myTypes,classify));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }
}
