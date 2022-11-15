package com.player.circle.service.imp;

import com.player.circle.mapper.CircleMapper;
import com.player.circle.service.ICircleService;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CircleService implements ICircleService {
    
    @Autowired
    private CircleMapper circleMapper;
    
    /**
     * @author: wuwenqiang
     * @description: 获取总评论数量
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getCircleList(int pageSize,int pageNum){
        return ResultUtil.success(circleMapper.getCircleList(pageSize,pageNum));
    }
}
