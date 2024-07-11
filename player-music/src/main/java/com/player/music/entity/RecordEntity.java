package com.player.music.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "music_record")
public class RecordEntity {
    @ApiModelProperty(value = "主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    @ApiModelProperty(value = "歌曲id")
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "albummid")
    @Column(name = "albummid")
    private String albummid;

    @ApiModelProperty(value = "歌曲时长")
    @Column(name = "duration")
    private Integer duration;

    @ApiModelProperty(value = "歌曲图片")
    @Column(name = "image")
    private String image;

    @ApiModelProperty(value = "mid")
    @Column(name = "mid")
    private String mid;

    @ApiModelProperty(value = "歌曲名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "歌手")
    @Column(name = "singer")
    private String singer;

    @ApiModelProperty(value = "歌曲播放地址，如果play_mode:local时，则使用local_url作为播放地址")
    @Column(name = "url")
    private String url;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "user_id")
    @Column(name = "user_id")
    private String userId;

    @ApiModelProperty(value = "播放次数")
    @Column(name = "timer")
    private Integer timer;
}
