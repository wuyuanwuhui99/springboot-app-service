package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "favorite_music")
public class FavoriteMusicEntity {
    @ApiModelProperty(value = "主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long tId;

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

    @ApiModelProperty(value = "播放次数")
    @Column(name = "timer")
    private Integer timer;

    @ApiModelProperty(value = "更新时间，按更新时间排序")
    @Column(name = "update_time")
    private Date updateTime;

    @ApiModelProperty(value = "酷狗播放地址")
    @Column(name = "kugou_url")
    private String kugouUrl;

    @ApiModelProperty(value = "播放模式，使用酷狗音乐的url或使用QQ音乐的url")
    @Column(name = "play_mode")
    private String playMode;

    @ApiModelProperty(value = "其他播放地址")
    @Column(name = "other_url")
    private String otherUrl;

    @ApiModelProperty(value = "本地播放地址")
    @Column(name = "local_url")
    private String localUrl;

    @ApiModelProperty(value = "是否禁用，1:禁用，0:不禁用")
    @Column(name = "disabled")
    private String disabled;

    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private String userId;

    @ApiModelProperty(value = "歌词")
    @Column(name = "lyric")
    private String lyric;

    @ApiModelProperty(value = "本地歌曲图片")
    @Column(name = "local_image")
    private String localImage;
}
