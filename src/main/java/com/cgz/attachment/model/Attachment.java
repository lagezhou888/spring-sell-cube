package com.cgz.attachment.model;

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
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Attachment extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    @ApiModelProperty("url")
    private String url;

    @ApiModelProperty("name")
    private String name;


}
