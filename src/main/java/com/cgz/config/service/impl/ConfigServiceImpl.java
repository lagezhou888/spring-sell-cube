package com.cgz.config.service.impl;

import com.cgz.config.model.Config;
import com.cgz.config.mapper.ConfigMapper;
import com.cgz.config.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caogzh
 * @since 2020-11-13
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

	@Autowired
	private ConfigMapper configMapper;
	
	@Override
	public List<Config> selectAll() {
		return configMapper.selectList();
	}

}
