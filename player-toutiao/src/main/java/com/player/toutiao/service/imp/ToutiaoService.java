package com.player.toutiao.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.Common;
import com.player.common.utils.JwtToken;
import com.player.toutiao.entity.ArticleEntity;
import com.player.toutiao.entity.ChannelEntity;
import com.player.toutiao.entity.CommentEntity;
import com.player.toutiao.mapper.ToutiaoMapper;
import com.player.toutiao.service.IToutiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ToutiaoService implements IToutiaoService {

    @Autowired
    private ToutiaoMapper toutiaoMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public ResultEntity getRequestData(String url,String token,HttpMethod type,Map<String, Object> params){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);
        HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(params),headers);
        ResponseEntity<ResultEntity> responseEntity = restTemplate.exchange(
                url,
                type,
                httpEntity,
                ResultEntity.class
        );
        return  responseEntity.getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 获取文章列表
     * @date: 2021-12-25 22:29
     */
    @Override
    public ResultEntity getArticleList(int pageNum, int pageSize,  String type, String channelId,String authorId, String keyword,String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            if(pageSize > 100)pageSize = 100;
            int start = pageSize * (pageNum-1);
            ResultEntity resultEntity = ResultUtil.success(toutiaoMapper.getArticleList(start,pageSize, type, channelId, authorId, keyword));
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.HOURS);
            return  resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 获取文章详情
     * @date: 2021-5-29 19:22
     */
    @Override
    public ResultEntity getArticleDetail(int id,String token) {
        String key = "articleId_" + id;
        String result = (String) redisTemplate.opsForValue().get(key);
        ResultEntity resultEntity;
        ArticleEntity articleEntity;
        if(!StringUtils.isEmpty(result)){
            resultEntity = JSON.parseObject(result,ResultEntity.class);
            articleEntity =  JSON.parseObject(JSON.toJSONString(resultEntity.getData()),ArticleEntity.class);
        }else{
            articleEntity = toutiaoMapper.getArticleDetail(id);
            resultEntity = ResultUtil.success(articleEntity);
            redisTemplate.opsForValue().set(key, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
        }
        String userId = JwtToken.getUserId(token);
        toutiaoMapper.saveArticleRecord(userId,articleEntity.getId());
        toutiaoMapper.deleteArticleRecord(userId);
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @description: 查询用户收藏的频道,如果没有查询到，讲用所有频道里面的数据插入到个人频道列表中
     * @date: 2021-5-29 19:22
     */
    @Override
    public ResultEntity getFavoriteChannels(String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        String key = "findFavoriteChannels_" + userEntity.getUserId();
        String result = (String) redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            List<ChannelEntity> favoriteChannels = toutiaoMapper.getFavoriteChannels(userEntity.getUserId());
            if (favoriteChannels.size() == 0){
                List<Integer> status = new ArrayList<>(Arrays.asList(0,1,2));//状态，公开:0,推荐:1,默认:2,非公开:3
                favoriteChannels = toutiaoMapper.getAllChannels(status);
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
     * @date: 2021-5-29 19:22
     */
    @Override
    public ResultEntity getAllChannels(List<Integer>  status) {
        return ResultUtil.success(toutiaoMapper.getAllChannels(status));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取用户信息
     * @date: 2021-5-29 19:22
     */
    @Override
    public ResultEntity getUserData(String token) {
        return getRequestData("http://player-user/service/user/getUserData",token,HttpMethod.GET,null);
    }

    /**
     * @author: wuwenqiang
     * @description: 获取历史记录
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity getRecordList(String token){
        return ResultUtil.success(toutiaoMapper.getArticleRecordList(JwtToken.getUserId(token)));
    }

    /**
     * @author: wuwenqiang
     * @description: 查询文章是否已经收藏
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity isFavorite(String token,int articleId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(toutiaoMapper.isFavorite(userId, articleId));
    }

    /**
     * @author: wuwenqiang
     * @description: 查询搜狐从列表
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity getFavoriteList(String token,int pageNum,int pageSize){
        String userId = JwtToken.getUserId(token);
        if(pageSize > 100) pageSize = 100;
        List<ArticleEntity> favorite = toutiaoMapper.getFavoriteList(userId,(pageNum-1)*pageSize, pageSize);
        return ResultUtil.success(favorite.size() > 0);
    }

    /**
     * @author: wuwenqiang
     * @description: 新增收藏
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity insertFavorite(String token,int articleId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(toutiaoMapper.insertFavorite(userId,articleId));
    }

    /**
     * @author: wuwenqiang
     * @description: 删除收藏
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity deleteFavorite(String token,int articleId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(toutiaoMapper.deleteFavorite(userId,articleId));
    }

    /**
     * @author: wuwenqiang
     * @description: 查询是否已经收视频
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity isLike(String token,int articleId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(toutiaoMapper.isLike(userId, articleId));
    }

    /**
     * @author: wuwenqiang
     * @description: 查询是否已经收视频
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity insertLike(String token,int articleId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(toutiaoMapper.insertLike(userId,articleId));
    }

    /**
     * @author: wuwenqiang
     * @description: 查询是否已经收视频
     * @date: 2021-08-14 22:29
     */
    @Override
    public ResultEntity deleteLike(String token,int articleId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(toutiaoMapper.deleteLike(userId,articleId));
    }

    /**
     * @author: wuwenqiang
     * @description: 查询是否已经关注
     * @date: 2021-08-20 23:08
     */
    @Override
    public ResultEntity isFocus(String token,String authorId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(toutiaoMapper.isFocus(userId, authorId));
    }

    /**
     * @author: wuwenqiang
     * @description: 添加关注
     * @date: 2021-08-20 23:08
     */
    @Override
    public ResultEntity insertFocus(String token,String authorId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(toutiaoMapper.insertFocus(userId,authorId));
    }

    /**
     * @author: wuwenqiang
     * @description: 取消关注
     * @date: 2021-08-20 23:08
     */
    @Override
    public ResultEntity deleteFocus(String token,String authorId){
        String userId = JwtToken.getUserId(token);
        return ResultUtil.success(toutiaoMapper.deleteFocus(userId,authorId));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取总评论数量
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getCommentCount(int articleId){
        return ResultUtil.success(toutiaoMapper.getCommentCount(articleId));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取一级评论列表
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getTopCommentList(int articleId,int pageNum, int pageSize){
        if(pageSize > 100)pageSize = 100;
        int start = (pageNum - 1)*pageSize;
        return ResultUtil.success(toutiaoMapper.getTopCommentList(articleId,start,pageSize));
    }

    /**
     * @author: wuwenqiang
     * @description: 新增评论
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity insertComment(String token,CommentEntity commentEntity){
        commentEntity.setUserId(JwtToken.getUserId(token));
        toutiaoMapper.insertComment(commentEntity);
        return ResultUtil.success(commentEntity.getId());
    }

    /**
     * @author: wuwenqiang
     * @description: 删除评论
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity deleteComment(int id,String userId){
        return ResultUtil.success(toutiaoMapper.deleteComment(id,userId));
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
        return ResultUtil.success(toutiaoMapper.getReplyCommentList(topId,start,pageSize));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取新增的单条评论或者回复
     * @date: 2021-08-22 15:20
     */
    @Override
    public ResultEntity getCommentItem(int id){
        return ResultUtil.success(toutiaoMapper.getCommentItem(id));
    }
}
