package com.cgz.userasset.service.impl;

import com.cgz.userasset.model.Userasset;
import com.cgz.userasset.mapper.UserassetMapper;
import com.cgz.userasset.service.IUserassetService;

import cn.hutool.core.convert.Convert;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caogzh
 * @since 2020-11-24
 */
@Service
public class UserassetServiceImpl extends ServiceImpl<UserassetMapper, Userasset> implements IUserassetService {

    /**
    * <p>
    * Field UserassetMapper: 引用搜索Mapper
    * </p>
    */
    @Autowired
    private UserassetMapper userassetMapper;

   /**
   * <p>Description: 搜索</p>
   * @param userasset 
   * @return 全部
   */
   @Override
   public List<Userasset> selectGetAll(@Param("et") Userasset userasset) {
        return userassetMapper.selectGetAll(userasset);
   }

    @Override
    public IPage<Userasset> queryPage(Page page, @Param("et") Userasset userasset) {
        return userassetMapper.queryPage(page,userasset);
        }

	@Override
	public List<Userasset> selectByUserId(String userId) {
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("userId", Convert.toInt(userId));
		List<Userasset> list = userassetMapper.selectByMap(columnMap);
		return list;
	}
    }
