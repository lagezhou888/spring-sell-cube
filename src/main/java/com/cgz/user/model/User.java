package com.cgz.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caogzh
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty("name")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty("age")
    private Integer age;

    /**
     * 性别
     */
    @ApiModelProperty("sex")
    private String sex;

    /**
     * 账号
     */
    @ApiModelProperty("account")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty("password")
    private String password;

    /**
     * 电话
     */
    @ApiModelProperty("phone")
    private String phone;

}
