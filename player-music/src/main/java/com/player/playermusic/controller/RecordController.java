package com.player.playermusic.controller;

import com.player.common.entity.ResultEntity;
import com.player.playermusic.Entity.RecordEntity;
import com.player.playermusic.service.IRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
@Api(value = "抖音查询和记录的接口", description = "查询抖音列表、播放记录接口")
public class RecordController {

    @Autowired
    private IRecordService recordService;

    /**
     * @author: wuwenqiang
     * @methodsName: record
     * @description: 播放记录
     * @param: recordEntity 歌曲的实体对象
     * @return: ResultEntityTransactional
     * @date: 2020-07-25 8:26
     */
    @ApiOperation("播放记录")
    @PostMapping("/music/record")
    public ResultEntity record(@RequestBody RecordEntity recordEntity) {
        return recordService.record(recordEntity);
    }
}
