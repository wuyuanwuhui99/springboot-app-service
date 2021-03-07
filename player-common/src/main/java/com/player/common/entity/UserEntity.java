package com.player.common.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", username='" + username + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", avater='" + avater + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
