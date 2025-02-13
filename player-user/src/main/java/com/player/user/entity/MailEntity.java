package com.player.user.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MailEntity {

    @ApiModelProperty(value = "发送的用户")
    private String email;

    @ApiModelProperty(value = "主题")
    private String subject;

    @ApiModelProperty(value = "发送的文本")
    private String text;

    @ApiModelProperty(value = "验证码")
    private String code;
}
