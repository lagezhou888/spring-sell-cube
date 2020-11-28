package com.cgz.business.service.impl;

import com.cgz.business.model.Business;
import com.cgz.business.mapper.BusinessMapper;
import com.cgz.business.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caogzh
 * @since 2020-11-28
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements IBusinessService {

    /**
    * <p>
    * Field BusinessMapper: 引用搜索Mapper
    * </p>
    */
    @Autowired
    private BusinessMapper businessMapper;

   /**
   * <p>Description: 搜索</p>
   * @param business 
   * @return 全部
   */
   @Override
   public List<Business> selectGetAll(@Param("et") Business business) {
        return businessMapper.selectGetAll(business);
   }

    @Override
    public IPage<Business> queryPage(Page page, @Param("et") Business business) {
        return businessMapper.queryPage(page,business);
        }
    }
