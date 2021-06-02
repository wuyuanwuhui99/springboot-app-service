package com.player.toutiao.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.JwtToken;
import com.player.toutiao.entity.ChannelEntity;
import com.player.toutiao.mapper.ToutiaoMapper;
import com.player.toutiao.service.IToutiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class Toutiaoservice implements IToutiaoService {

    @Autowired
    private ToutiaoMapper toutiaoMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author: wuwenqiang
     * @description: 获取文章列表
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity findArticleList(int pageSize, int pageNum, String type, String keyword) {
        int start = pageSize * pageNum;
        return ResultUtil.success(toutiaoMapper.findArticleList(start,pageSize, type, keyword));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取文章详情
     * @date: 2020-5-29 19:22
     */
    @Override
    public ResultEntity findArticleDetail(int id,String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(toutiaoMapper.findArticleDetail(id));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 查询用户收藏的频道,如果没有查询到，讲用所有频道里面的数据插入到个人频道列表中
     * @date: 2020-5-29 19:22
     */
    @Override
    public ResultEntity findFavoriteChannels(String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        String key = "findFavoriteChannels_" + userEntity.getUserId();
        String result = (String) redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            List<ChannelEntity> favoriteChannels = toutiaoMapper.findFavoriteChannels(userEntity.getUserId());
            if (favoriteChannels.size() == 0){
                List<Integer> status = new ArrayList<>(Arrays.asList(0,1,2));//状态，公开:0,推荐:1,默认:2,非公开:3
                favoriteChannels = toutiaoMapper.findAllChannels(status);
                favoriteChannels.forEach((favoriteChannel)->{
                    favoriteChannel.setUserId(userEntity.getUserId());
                });
                toutiaoMapper.insertFavoriteChannels(favoriteChannels);
            }
            ResultEntity resultEntity  = ResultUtil.success(favoriteChannels);
            redisTemplate.opsForValue().set(key, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 查询所有品达
     * @date: 2020-5-29 19:22
     */
    @Override
    public ResultEntity findAllChannels(List<Integer>  status) {
        return ResultUtil.success(toutiaoMapper.findAllChannels(status));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取用户信息
     * @date: 2020-5-29 19:22
     */
    @Override
    public ResultEntity getUserData(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        ResponseEntity<ResultEntity> responseEntity = restTemplate.exchange(
                "http://player-movie/service/movie/getUserData",
                HttpMethod.GET,
                new HttpEntity<String>(headers),ResultEntity.class
        );
        return  responseEntity.getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 根据用户id查询用户的文章
     * @date: 2020-5-29 19:22
     */
    @Override
    public ResultEntity findArticleByUserId(String userId,int pageNum,int pageSize,String keyword) {
        int start = pageNum * pageSize;
        return ResultUtil.success(toutiaoMapper.findArticleByUserId(userId,start,pageSize,keyword));
    }
}
