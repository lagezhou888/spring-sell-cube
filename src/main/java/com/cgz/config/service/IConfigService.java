package com.cgz.config.service;

import com.cgz.config.model.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caogzh
 * @since 2020-11-13
 */
public interface IConfigService extends IService<Config> {

	public List<Config> selectAll();

}
