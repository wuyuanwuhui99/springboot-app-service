package com.player.movie.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.JwtToken;
import com.player.common.utils.ResultCode;
import com.player.movie.entity.MovieEntity;
import com.player.movie.mapper.MovieMapper;
import com.player.movie.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResultEntity updateUser(UserEntity userEntity) {
       return ResultUtil.success(movieMapper.updateUser(userEntity));
    }

    /**
     * @author: wuwenqiang
     * @description: 修改密码
     * @date: 2020-12-24 22:40
     */
    @Override
    public ResultEntity updatePassword(String userId,String newPassword,String oldPassword) {
        return ResultUtil.success(movieMapper.updatePassword(userId,newPassword,oldPassword));
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
//        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
//        paramMap.add("userId", userEntity.getUserId());
//        paramMap.add("password", userEntity.getPassword());
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,headers);
//        ResponseEntity<ResultEntity> responseEntity = restTemplate.exchange(
//                "http://player-user/service/user/login",
//                HttpMethod.POST,
//                httpEntity,
//                ResultEntity.class
//        );
//        return  responseEntity.getBody();

        URI uri = UriComponentsBuilder.fromUriString("http://player-user/service/user/login").build().toUri();
        // 自定义body实体类
        String s = JSON.toJSONString(userEntity);
        RequestEntity<String> requestEntity = RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(s);
        ResponseEntity<ResultEntity> responseEntity = restTemplate.exchange(requestEntity,ResultEntity.class);
        return  responseEntity.getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 注册
     * @date: 2021-01-01 23:39
     */
    @Override
    public ResultEntity register(UserEntity userEntity) {
        userEntity.setCreateDate(new Date());
        userEntity.setUpdateDate(new Date());
        Long rows = movieMapper.register(userEntity);
        if (rows >= 1) {
            return ResultUtil.success(movieMapper.getUserById(userEntity.getUserId()));
        }
        return ResultUtil.fail(null, "注册失败");
    }

    /**
     * @author: wuwenqiang
     * @description: 查询单个用户，用于校验用户是否存在
     * @date: 2021-01-01 23:39
     */
    @Override
    public ResultEntity getUserById(String userId) {
        return ResultUtil.success(movieMapper.getUserById(userId));
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
        ResponseEntity<ResultEntity> responseEntity = restTemplate.exchange(
                "http://player-user/service/user/getUserData",
                HttpMethod.GET,
                new HttpEntity<String>(headers),ResultEntity.class
        );
        return  responseEntity.getBody();
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
    public ResultEntity search(String keyword, int pageNum, int pageSize,String path) {
        String url = path + "?keyword=" + keyword + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            int start = (pageNum - 1) * pageSize;
            Map<String, Long> totalMap = movieMapper.total(keyword);
            ResultEntity resultEntity =  ResultUtil.success(movieMapper.search(keyword, start, pageSize), totalMap.get("total"));
            redisTemplate.opsForValue().set(url, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }


    /**
     * @author: wuwenqiang
     * @description: 播放记录
     * @date: 2020-12-26 10:41
     */
    @Override
    public ResultEntity getStar(String movieId,String path) {
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
    public ResultEntity getMovieUrl(String movieId,String path) {
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
     * @description: 获取浏览记录
     * @date: 2021-02-28 12:08
     */
    @Override
    public ResultEntity getViewRecord(String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        ResultEntity resultEntity = ResultUtil.success(movieMapper.getViewRecord(userEntity.getUserId()));
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @description: 保存浏览记录
     * @date: 2021-02-28 12:08
     */
    @Override
    @Transactional
    public ResultEntity saveViewRecord(MovieEntity movieEntity,String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        Date date = new Date();
        movieEntity.setCreateTime(date);
        movieEntity.setUpdateTime(date);
        movieEntity.setUserId(userEntity.getUserId());
        ResultEntity resultEntity = ResultUtil.success(movieMapper.saveViewRecord(movieEntity));
        return resultEntity;
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
    public ResultEntity getFavoriteList(String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(movieMapper.getFavoriteList(userEntity.getUserId()));
    }

    /**
     * @author: wuwenqiang
     * @description: 添加收藏
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity saveFavorite(MovieEntity movieEntity, String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        Date date = new Date();
        movieEntity.setCreateTime(date);
        movieEntity.setUpdateTime(date);
        movieEntity.setUserId(userEntity.getUserId());
        return ResultUtil.success(movieMapper.saveFavorite(movieEntity));
    }

    /**
     * @author: wuwenqiang
     * @description: 删除收藏
     * @date: 2021-03-07 16:10
     */
    @Override
    public ResultEntity deleteFavorite(String movieId,String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(movieMapper.deleteFavorite(movieId,userEntity.getUserId()));
    }

    @Override
    public ResultEntity isFavorite(String movieId,String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(movieMapper.isFavorite(movieId,userEntity.getUserId()));
    }

    @Override
    public ResultEntity getYourLikes(String labels,String path) {
        String url = path + "?labels=" + labels;
        String result = (String) redisTemplate.opsForValue().get(url);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            labels = labels.replaceAll("^/|/$","");
            String[] myLabels = labels.split("/");
            ResultEntity resultEntity = ResultUtil.success(movieMapper.getYourLikes(myLabels));
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
}
