package com.player.music.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.JwtToken;
import com.player.music.mapper.MyMusicMapper;
import com.player.music.service.IMyMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class MyMusicService implements IMyMusicService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MyMusicMapper myMusicMapper;

    /**
     * @author: wuwenqiang
     * @methodsName: getKeywordMusic
     * @description: 获取搜索框中推荐的音乐
     * @return: String
     * @date: 2023-05-25 20:55
     */
    @Override
    public ResultEntity getKeywordMusic(String redisKey) {
        String result = (String) redisTemplate.opsForValue().get(redisKey);
        if(!StringUtils.isEmpty(result)){
            return JSON.parseObject(result,ResultEntity.class);
        }else{
            ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getKeywordMusic());
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONStringWithDateFormat(resultEntity, "yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @methodsName: getKeywordMusic
     * @description: 获取推荐音乐列表
     * @return: String
     * @date: 2023-05-25 21:00
     */
    @Override
    public ResultEntity getMusicClassify(String redisKey) {
        String result = (String) redisTemplate.opsForValue().get(redisKey);
        if(!StringUtils.isEmpty(result)){
            return JSON.parseObject(result,ResultEntity.class);
        }else{
            ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getMusicClassify());
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONStringWithDateFormat(resultEntity, "yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @methodsName: getKeywordMusic
     * @description: 获取推荐音乐列表
     * @return: String
     * @date: 2023-05-25 21:00
     */
    @Override
    public ResultEntity getMusicListByClassifyId(String redisKey,int classifyId,int pageNum,int pageSize,String token) {
        String userId = "";
        if(!StringUtils.isEmpty(token)){
            userId =  JwtToken.parserToken(token, UserEntity.class).getUserId();
        }
        redisKey += "?classifyId="+classifyId+"&pageNum=" + pageNum + "&pageSize=" + pageNum + "&userId=" + userId;
        String result = (String) redisTemplate.opsForValue().get(redisKey);
        if(!StringUtils.isEmpty(result)){
            return JSON.parseObject(result,ResultEntity.class);
        }else{
            if(pageSize > 100)pageSize = 100;
            int start = (pageNum - 1) * pageSize;
            ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getMusicListByClassifyId(classifyId,start,pageSize,userId));
            Long musicTotalByClassifyId = myMusicMapper.getMusicTotalByClassifyId(classifyId);
            resultEntity.setTotal(musicTotalByClassifyId);
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONStringWithDateFormat(resultEntity, "yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    @Override
    public ResultEntity getSingerList(String redisKey, int pageNum, int pageSize) {
        redisKey += "?pageNum=" + pageNum + "&pageSize=" + pageNum;
        String result = (String) redisTemplate.opsForValue().get(redisKey);
        if(!StringUtils.isEmpty(result)){
            return JSON.parseObject(result,ResultEntity.class);
        }else{
            if(pageSize > 100)pageSize = 100;
            int start = (pageNum - 1) * pageSize;
            ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getSingerList(start,pageSize));
            Long singerTotal = myMusicMapper.getSingerTotal();
            resultEntity.setTotal(singerTotal);
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONStringWithDateFormat(resultEntity, "yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }
}
