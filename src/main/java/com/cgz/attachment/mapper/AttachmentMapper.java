package com.cgz.attachment.mapper;

import com.cgz.attachment.model.Attachment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caogzh
 * @since 2020-11-16
 */
public interface AttachmentMapper extends BaseMapper<Attachment> {

	public List<Attachment> selectByUserId(Integer userId);

	public Boolean deleteByUserId(Integer userId);

}
