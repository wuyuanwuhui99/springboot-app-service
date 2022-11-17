package com.player.circle.service.imp;

import com.player.circle.mapper.CircleMapper;
import com.player.circle.service.ICircleService;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.player.common.utils.HttpUtils.getRequestData;

@Service
public class CircleService implements ICircleService {
    
    @Autowired
    private CircleMapper circleMapper;

    @Autowired
    private RestTemplate restTemplate;
    
    /**
     * @author: wuwenqiang
     * @description: 获取电影圈列表
     * @date: 2022-11-17 23:15
     */
    @Override
    public ResultEntity getCircleArticleList(int pageSize,int pageNum){
        return ResultUtil.success(circleMapper.getCircleArticleList(pageSize,pageNum));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取用户信息
     * @date: 2022-11-17 23:14
     */
    @Override
    public ResultEntity getUserData(String token) {
        return getRequestData(restTemplate,"http://player-user/service/user/getUserData",token, HttpMethod.GET,null);
    }
}
