package com.player.music.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.JwtToken;
import com.player.music.Entity.MyMusicEntity;
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
        if (!StringUtils.isEmpty(result)) {
            return JSON.parseObject(result, ResultEntity.class);
        } else {
            ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getKeywordMusic());
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONStringWithDateFormat(resultEntity, "yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat), 1, TimeUnit.DAYS);
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
        if (!StringUtils.isEmpty(result)) {
            return JSON.parseObject(result, ResultEntity.class);
        } else {
            ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getMusicClassify());
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONStringWithDateFormat(resultEntity, "yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat), 1, TimeUnit.DAYS);
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
    public ResultEntity getMusicListByClassifyId(String redisKey, int classifyId, int pageNum, int pageSize, boolean isRedis, String token) {
        String userId = "";
        if (!StringUtils.isEmpty(token)) {
            userId = JwtToken.parserToken(token, UserEntity.class).getUserId();
        }
        if(isRedis){
            redisKey += "?classifyId=" + classifyId + "&pageNum=" + pageNum + "&pageSize=" + pageNum + "&userId=" + userId;
            String result = (String) redisTemplate.opsForValue().get(redisKey);
            if (!StringUtils.isEmpty(result)) {// 如果缓存中有数据
                return JSON.parseObject(result, ResultEntity.class);
            } else {// 如果缓存中没有数据，从数据库中查询，并将查询结果写入缓存
                ResultEntity resultEntity = findMusicListByClassifyId(classifyId, pageNum, pageSize, userId);
                redisTemplate.opsForValue().set(redisKey, JSON.toJSONStringWithDateFormat(resultEntity, "yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat), 1, TimeUnit.DAYS);
                return resultEntity;
            }
        }else{
            return findMusicListByClassifyId(classifyId, pageNum, pageSize,userId);
        }
    }

    private ResultEntity findMusicListByClassifyId(int classifyId, int pageNum, int pageSize,String userId){
        if (pageSize > 100) pageSize = 100;
        int start = (pageNum - 1) * pageSize;
        ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getMusicListByClassifyId(classifyId, start, pageSize, userId));
        Long musicTotalByClassifyId = myMusicMapper.getMusicTotalByClassifyId(classifyId);
        resultEntity.setTotal(musicTotalByClassifyId);
        return resultEntity;
    }

    @Override
    public ResultEntity getSingerList(String redisKey, int pageNum, int pageSize) {
        redisKey += "?pageNum=" + pageNum + "&pageSize=" + pageSize;
        String result = (String) redisTemplate.opsForValue().get(redisKey);
        if (!StringUtils.isEmpty(result)) {
            return JSON.parseObject(result, ResultEntity.class);
        } else {
            if (pageSize > 100) pageSize = 100;
            int start = (pageNum - 1) * pageSize;
            ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getSingerList(start, pageSize));
            Long singerTotal = myMusicMapper.getSingerTotal();
            resultEntity.setTotal(singerTotal);
            return resultEntity;
        }
    }
    @Override
    public ResultEntity getMusiPlayMenu(String redisKey,String token){
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        redisKey += "?userId=" + userEntity.getUserId();
        String result = (String) redisTemplate.opsForValue().get(redisKey);
        if (!StringUtils.isEmpty(result)) {
            return JSON.parseObject(result, ResultEntity.class);
        } else {
            ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getMusiPlayMenu(userEntity.getUserId()));
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONStringWithDateFormat(resultEntity, "yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat), 1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    @Override
    public ResultEntity getMySinger(String redisKey,String token,int pageNum, int pageSize){
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        redisKey += "?userId=" + userEntity.getUserId() + "pageNum=" + pageNum + "&pageSize=" + pageSize;
        String result = (String) redisTemplate.opsForValue().get(redisKey);
        if (!StringUtils.isEmpty(result)) {
            return JSON.parseObject(result, ResultEntity.class);
        } else {
            if (pageSize > 100) pageSize = 100;
            int start = (pageNum - 1) * pageSize;
            ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getMySinger(userEntity.getUserId(),start,pageSize));
            Long mySingerCount = myMusicMapper.getMySingerCount(userEntity.getUserId());
            resultEntity.setTotal(mySingerCount);
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONStringWithDateFormat(resultEntity, "yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat), 1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    @Override
    public ResultEntity getMusicRecord(String token, int pageNum, int pageSize){
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        if (pageSize > 100) pageSize = 100;
        int start = (pageNum - 1) * pageSize;
        ResultEntity resultEntity = ResultUtil.success(myMusicMapper.getMusicRecord(userEntity.getUserId(),start,pageSize));
        Long mySingerCount = myMusicMapper.getMusicRecordCount(userEntity.getUserId());
        resultEntity.setTotal(mySingerCount);
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @methodsName: insertLog
     * @description: 插入播放记录
     * @return: ResultEntity
     * @date: 2023-11-20 21:52
     */
    @Override
    public ResultEntity insertLog(String token, MyMusicEntity myMusicEntity){
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        Long musicId = myMusicEntity.getId();
        return ResultUtil.success(myMusicMapper.insertLog(userEntity.getUserId(),musicId));
    }

    /**
     * @author: wuwenqiang
     * @methodsName: insertLog
     * @description: 插入播放记录
     * @return: ResultEntity
     * @date: 2024-01-05 21:50
     */
    @Override
    public ResultEntity insertMusicFavorite(String token, MyMusicEntity myMusicEntity){
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(myMusicMapper.insertMusicFavorite(userEntity.getUserId(), myMusicEntity.getId()));
    }

    /**
     * @author: wuwenqiang
     * @methodsName: insertLog
     * @description: 删除收藏
     * @return: ResultEntity
     * @date: 2024-01-05 21:50
     */
    @Override
    public ResultEntity deleteMusicFavorite(String token, Long id){
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(myMusicMapper.deleteMusicFavorite(userEntity.getUserId(),id));
    }

    /**
     * @author: wuwenqiang
     * @methodsName: queryMusicFavorite
     * @description: 查询收藏
     * @return: ResultEntity
     * @date: 2024-01-05 21:50
     */
    @Override
    public ResultEntity queryMusicFavorite(String token, int pageNum, int pageSize){
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        if (pageSize > 100) pageSize = 100;
        int start = (pageNum - 1) * pageSize;
        ResultEntity resultEntity = ResultUtil.success(myMusicMapper.queryMusicFavorite(userEntity.getUserId(),start,pageSize));
        Long mySingerCount = myMusicMapper.queryMusicFavoriteCount(userEntity.getUserId());
        resultEntity.setTotal(mySingerCount);
        return resultEntity;
    }
}
