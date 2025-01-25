package com.player.user.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ResetPasswordEntity {

    @ApiModelProperty(value = "接收人")
    private String email;

    @ApiModelProperty(value = "验证码")
    private int code;

    @ApiModelProperty(value = "新密码")
    private String password;
}
