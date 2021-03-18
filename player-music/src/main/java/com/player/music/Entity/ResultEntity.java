package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResultEntity {

    @ApiModelProperty(value = "数据")
    private Object data;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "信息")
    private String msg;
}
