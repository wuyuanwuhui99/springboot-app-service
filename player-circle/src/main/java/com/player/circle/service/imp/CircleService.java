package com.player.circle.service.imp;

import com.player.circle.entity.CircleEntity;
import com.player.circle.entity.LogCircleEntity;
import com.player.circle.mapper.CircleMapper;
import com.player.circle.service.ICircleService;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.player.common.utils.HttpUtils.getRequestData;

@Service
public class CircleService implements ICircleService {

    @Autowired
    private CircleMapper circleMapper;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @author: wuwenqiang
     * @description: 获取电影圈列表,插入日志表
     * @date: 2022-11-17 23:15
     */
    @Override
    public ResultEntity getCircleArticleList(int pageNum, int pageSize, String type,String keyword,String token) {
        int start = (pageNum - 1) * pageSize;
        List<CircleEntity>circleEntities = circleMapper.getCircleArticleList(start, pageSize, type);
        Long total = circleMapper.getCircleCount("movie",keyword);
        List<LogCircleEntity>logCircleEntities = new ArrayList<>();
        String userId = JwtToken.getUserId(token);
        for(CircleEntity circleEntity:circleEntities){
            LogCircleEntity logCircleEntity = new LogCircleEntity();
            logCircleEntity.setCircleId(circleEntity.getId());
            logCircleEntity.setUserId(userId);
            logCircleEntities.add(logCircleEntity);
        }
        circleMapper.insertLog(logCircleEntities);
        ResultEntity resultEntity = ResultUtil.success(circleMapper.getCircleArticleList(start, pageSize, type));
        resultEntity.setTotal(total);
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @description: 获取用户信息
     * @date: 2022-11-17 23:14
     */
    @Override
    public ResultEntity getUserData(String token) {
        return getRequestData(restTemplate, "http://player-user/service/user/getUserData", token, HttpMethod.GET, null);
    }

    @Override
    public ResultEntity getCircleArticleCount(int id) {
        return ResultUtil.success(circleMapper.getCircleArticleCount(id));
    }
}
