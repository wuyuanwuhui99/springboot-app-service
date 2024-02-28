package com.player.music.Entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MyMusicSingerCategoryEntity {
    @ApiModelProperty(value = "分类名称")
    private String category;
}
