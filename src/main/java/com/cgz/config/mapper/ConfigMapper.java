package com.cgz.config.mapper;

import com.cgz.config.model.Config;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caogzh
 * @since 2020-11-13
 */
public interface ConfigMapper extends BaseMapper<Config> {

	public List<Config> selectList();
	
}
