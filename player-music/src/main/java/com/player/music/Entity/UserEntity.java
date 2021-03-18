package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @ApiModelProperty(value = "主键")
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "update_date")
    private Date update_date;

    @Column(name = "username")
    private String username;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "avater")
    private String avater;

    @Column(name = "age")
    private String age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "role")
    private String role;

    @Column(name = "secret_key")
    private String secret_key;
}
