package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MusicClassifyEntity {

    @ApiModelProperty(value = "分类名称")
    private String classifyName;

    @ApiModelProperty(value = "分类排名")
    private Long classifyRank;
}
