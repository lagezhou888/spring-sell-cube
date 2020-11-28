package com.cgz.business.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Business对象", description="")
public class Business extends Model {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商家名称")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "姓名拼音")
    private String pinyin;

    @ApiModelProperty(value = "预定量")
    @TableField("advanceNum")
    private Integer advanceNum;

    @ApiModelProperty(value = "已收量")
    @TableField("receivedNum")
    private Integer receivedNum;

    @ApiModelProperty(value = "信用标签")
    private String tag;

    @ApiModelProperty(value = "是否收购")
    @TableField("isAcquisition")
    private Boolean isAcquisition;

    @ApiModelProperty(value = "图片路径")
    @TableField("imgUrl")
    private String imgUrl;


}
