package com.player.social.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "movie_recommend")
public class HotCommentMovieEntity implements Serializable {
    @ApiModelProperty(value = "主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "href")
    private String href;

    @Column(name = "label")
    private String label;

    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "rank")
    private int rank;

    @Column(name = "classify")
    private String classify;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
