package com.player.user.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MailEntity {
    /**
     * 接收人
     */
    @ApiModelProperty(value = "发送的用户")
    private String email;

    /**
     *  邮件主题
     */
    @ApiModelProperty(value = "主题")
    private String subject;

    /**
     *  邮件内容
     */
    @ApiModelProperty(value = "发送的文本")
    private String text;
}
