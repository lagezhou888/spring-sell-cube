package com.cgz.userasset.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
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
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Userasset对象", description="")
public class Userasset extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "水果名称")
    private String name;

    @ApiModelProperty(value = "是否种植")
    @TableField("isChecked")
    private String isChecked;

    @ApiModelProperty(value = "选择种植的水果种类ID")
    @TableField("selectId")
    private Integer selectId;

    @ApiModelProperty(value = "选择种植的水果种类名称")
    @TableField("selectName")
    private String selectName;


}
