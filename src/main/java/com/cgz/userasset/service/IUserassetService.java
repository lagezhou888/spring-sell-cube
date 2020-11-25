package com.cgz.userasset.service;

import com.cgz.userasset.model.Userasset;
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
 * @since 2020-11-24
 */
public interface IUserassetService extends IService<Userasset> {
     /**
     * <p>Description: 搜索</p>
     * @param userasset 
     * @return 查询全部
     */
     List<Userasset> selectGetAll(@Param("et") Userasset userasset);

     IPage<Userasset>  queryPage(Page page, @Param("et") Userasset userasset);

	 public List<Userasset> selectByUserId(@Param("et") String userId);

    }
