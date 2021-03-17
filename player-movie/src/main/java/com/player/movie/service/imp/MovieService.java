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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MovieService implements IMovieService {
    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration-time}")
    private Long expirationTime;

    private JwtToken jwtToken;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author: wuwenqiang
     * @description: 查询电影分类
     * @date: 2020-12-24 22:40
     */
    @Override
    public ResultEntity findClassify(String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
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
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(movieMapper.getKeyWord(classify));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 登录校验
     * @date: 2020-12-26 10:53
     */
    @Override
    public ResultEntity login(String userId, String passsword) {
        UserEntity userEntity = movieMapper.login(userId, passsword);
        if (userEntity != null) {

            if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
            String token = jwtToken.createToken(userEntity);//token有效期一天
            return ResultUtil.success(movieMapper.login(userId, passsword), "登录成功", token);
        } else {
            return ResultUtil.fail(null, "登录失败，账号或密码错误", ResultCode.LOGOUT);
        }
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
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = null;
        if (token == null || StringUtils.isEmpty(token)) {
            userEntity = movieMapper.getUserData();//如果用户签名为空，随机从数据库中查询一个公共的账号
        } else {
            userEntity = jwtToken.parserToken(token, UserEntity.class);
            if (userEntity == null) {//如果用户签名为空，随机从数据库中查询一个公共的账号
                userEntity = movieMapper.getUserData();
            }
        }
        String newToken = jwtToken.createToken(userEntity);
        return ResultUtil.success(userEntity, null, newToken);
    }

    /**
     * @author: wuwenqiang
     * @description: 获取用户访问记录
     * @date: 2020-12-21 22:40
     */
    @Override
    public ResultEntity getUserMsg(String token) {
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(movieMapper.getUserMsg(userEntity.getUserId()));
    }

    /**
     * @author: wuwenqiang
     * @description: 按classify大类查询所有catory小类
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity getAllCategoryByClassify(String classsify,String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(movieMapper.getAllCategoryByClassify(classsify));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
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
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity =ResultUtil.success(movieMapper.getAllCategoryListByPageName(pageName));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
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
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity =  ResultUtil.success(movieMapper.getCategoryList(classify, category));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
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
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            int start = (pageNum - 1) * pageSize;
            Map<String, Long> totalMap = movieMapper.total(keyword);
            ResultEntity resultEntity =  ResultUtil.success(movieMapper.search(keyword, start, pageSize), totalMap.get("total"));
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
    public ResultEntity getStar(String movieId,String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity =  ResultUtil.success(movieMapper.getStar(movieId));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
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
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity =  ResultUtil.success(movieMapper.getMovieUrl(movieId));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
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
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(userEntity == null){
            return ResultUtil.fail(null,"用户错误");
        }
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
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(userEntity == null){
            return ResultUtil.fail(null,"用户错误");
        }
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
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(userEntity == null){
            return ResultUtil.fail(null,"用户错误");
        }
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
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(userEntity == null){
            return ResultUtil.fail(null,"用户错误");
        }
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
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(userEntity == null){
            return ResultUtil.fail(null,"用户错误");
        }
        return ResultUtil.success(movieMapper.getFavoriteList(userEntity.getUserId()));
    }

    /**
     * @author: wuwenqiang
     * @description: 添加收藏
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity saveFavorite(MovieEntity movieEntity, String token) {
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(userEntity == null){
            return ResultUtil.fail(null,"用户错误");
        }
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
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(userEntity == null){
            return ResultUtil.fail(null,"用户错误");
        }
        return ResultUtil.success(movieMapper.deleteFavorite(movieId,userEntity.getUserId()));
    }

    @Override
    public ResultEntity isFavorite(String movieId,String token) {
        if(jwtToken == null) jwtToken = new JwtToken(secret,expirationTime);
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(userEntity == null){
            return ResultUtil.fail(null,"用户错误");
        }
        return ResultUtil.success(movieMapper.isFavorite(movieId,userEntity.getUserId()));
    }
}
