package com.cgz.business.mapper;

import com.cgz.business.model.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caogzh
 * @since 2020-11-28
 */
public interface BusinessMapper extends BaseMapper<Business> {
      /**
        * <p>Description: 搜索</p>
        * @param business 
        * @return 全部
      */
      List<Business> selectGetAll(@Param("et") Business business);

      IPage<Business> queryPage(Page page, @Param("et") Business business);

    }
