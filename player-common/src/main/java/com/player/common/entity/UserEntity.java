package com.player.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class UserEntity {
    @ApiModelProperty(value = "用户id，主键")
    private String userId;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "更新日期")
    private Date updateDate;

    @ApiModelProperty(value = "昵称")
    private String username;

    @ApiModelProperty(value = "电话号码")
    private String telephone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avater;

    @ApiModelProperty(value = "出生年月日")
    private String birthday;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "个性签名")
    private String sign;

    @ApiModelProperty(value = "地区")
    private String region;

    @ApiModelProperty(value = "是否禁用")
    private int disabled;
}
