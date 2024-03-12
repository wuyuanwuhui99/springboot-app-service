package com.player.ebook.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.ebook.entity.BookEntity;
import com.player.ebook.mapper.BookMapper;
import com.player.ebook.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author: wuwenqiang
     * @description: 分页查询电子书接口
     * @date: 2021-01-30 20:58
    */
    @Override
    public ResultEntity findBookList(
            Integer pageSize,
            Integer pageNum,
            String classify,
            String category,
            String keyword,
            String path
    ) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            Long listTatal = bookMapper.findBookListTotal(classify,category, keyword);
            List<BookEntity> bookList = bookMapper.findBookList(pageSize,pageNum,classify,category,keyword);
            ResultEntity resultEntity = ResultUtil.success(bookList,listTatal);
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    @Override
    public ResultEntity findAllByClassifyGroup(String token) {
        String path = "/service/ebook/findAllByClassifyGroup";
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(bookMapper.findAllByClassifyGroup());
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    @Override
    public ResultEntity getBanner(String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(bookMapper.getBanner());
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }
}
