package com.player.video.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.utils.JwtToken;
import com.player.video.entity.ChannelEntity;
import com.player.video.entity.VideoEntity;
import com.player.video.mapper.VideoMapper;
import com.player.video.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class VideoService implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    private JwtToken jwtToken = new JwtToken();

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
                "http://player-user/service/user/getUserData",
                HttpMethod.GET,
                new HttpEntity<String>(headers),ResultEntity.class
        );
        return  responseEntity.getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 获取用户信息
     * @date: 2020-5-29 19:22
     */
    @Override
    public ResultEntity getFavoriteChannels(String token) {
        String userId = jwtToken.getUserId(token);
        if(userId == null){
            return ResultUtil.fail(null,"你未登陆，请先登陆");
        }
        String key = "favorite_" + userId;
        String result = (String) redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            List<ChannelEntity> favoriteChannels = videoMapper.getFavoriteChannels(userId);
            if (favoriteChannels.size() == 0){
                favoriteChannels = videoMapper.getPublicChannels();
                videoMapper.insertFavoriteChannels(favoriteChannels);
            }
            ResultEntity resultEntity = ResultUtil.success(favoriteChannels);
            redisTemplate.opsForValue().set(key, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return ResultUtil.success(favoriteChannels);
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 获取文章列表
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity getVideoList(int pageNum,int pageSize,String star,String category,String type,String label,String userId,String keyword,String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            int start = pageSize * (pageNum-1);
            ResultEntity resultEntity = ResultUtil.success(videoMapper.getVideoList(start,pageSize,star,category,type,label,userId,keyword));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.HOURS);
            return  resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 插入收藏的频道
     * @date: 2020-7-4 11:32
     */
    @Override
    public ResultEntity insertFavoriteChannels(List<ChannelEntity> channelEntities){
        return ResultUtil.success(videoMapper.insertFavoriteChannels(channelEntities));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取是否收藏
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity isFavorite(String token,int id){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(videoMapper.isFavorite(userId, id));
    }

    /**
     * @author: wuwenqiang
     * @description: 保存收藏
     * @date: 2021-08-15 20:32
     */
    @Override
    public ResultEntity insertFavorite(String token,int videoId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(videoMapper.insertFavorite(userId, videoId));
    }

    /**
     * @author: wuwenqiang
     * @description: 取消收藏
     * @date: 2021-08-15 20:32
     */
    @Override
    public ResultEntity deleteFavorite(String token,int videoId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(videoMapper.deleteFavorite(userId, videoId));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取是否点赞
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity isLike(String token,int id){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(videoMapper.isLike(userId, id));
    }

    /**
     * @author: wuwenqiang
     * @description: 保存点赞
     * @date: 2021-08-15 20:32
     */
    @Override
    public ResultEntity insertLike(String token,int videoId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(videoMapper.insertLike(userId, videoId));
    }

    /**
     * @author: wuwenqiang
     * @description: 取消点赞
     * @date: 2021-08-15 20:32
     */
    @Override
    public ResultEntity deleteLike(String token,int videoId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(videoMapper.deleteLike(userId, videoId));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取是否关注
     * @date: 2021-08-20 23:20
     */
    @Override
    public ResultEntity isFocus(String token,String authorId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(videoMapper.isFocus(userId, authorId));
    }

    /**
     * @author: wuwenqiang
     * @description: 新增关注
     * @date: 2021-08-20 23:20
     */
    @Override
    public ResultEntity insertFocus(String token,String authorId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(videoMapper.insertFocus(userId, authorId));
    }

    /**
     * @author: wuwenqiang
     * @description: 取消关注
     * @date: 2021-08-20 23:20
     */
    @Override
    public ResultEntity deleteFocus(String token,String authorId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(videoMapper.deleteFocus(userId, authorId));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取收藏的视频
     * @date: 2021-08-16 22:53
     */
    @Override
    public ResultEntity getFavoriteList(String token, int pageNum, int pageSize){
        if(pageSize > 100)pageSize = 100;
        int start = (pageNum - 1)*pageSize;
        return ResultUtil.success(videoMapper.getFavoriteList(JwtToken.getUserId(token),start,pageSize));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取视频播放记录
     * @date: 2021-08-16 22:53
     */
    @Override
    public ResultEntity getVideoRecordList(String token){
        return ResultUtil.success(videoMapper.getVideoRecordList(JwtToken.getUserId(token)));
    }
}
