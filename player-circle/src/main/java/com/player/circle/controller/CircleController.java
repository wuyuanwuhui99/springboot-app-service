package com.player.circle.controller;

import com.player.circle.service.ICircleService;
import com.player.common.entity.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/service")
@Api(value = "电影圈", description = "新增，查询，修改，删除电影圈")
@RestController
public class CircleController {
    @Autowired
    private ICircleService circleService;

    @ApiOperation("获取电影圈列表")
    @GetMapping("/circle/getCircleList")
    public ResultEntity getCommentCount(
            @RequestParam("pageSize") int pageSize,
            @RequestParam("pageNum") int pageNum
    ) {
        return circleService.getCircleList(pageSize,pageNum);
    }
}
