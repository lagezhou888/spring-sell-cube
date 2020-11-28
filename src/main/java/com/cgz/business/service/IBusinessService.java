package com.cgz.business.service;

import com.cgz.business.model.Business;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caogzh
 * @since 2020-11-28
 */
public interface IBusinessService extends IService<Business> {
     /**
     * <p>Description: 搜索</p>
     * @param business 
     * @return 查询全部
     */
     List<Business> selectGetAll(@Param("et") Business business);

     IPage<Business>  queryPage(Page page, @Param("et") Business business);

    }
