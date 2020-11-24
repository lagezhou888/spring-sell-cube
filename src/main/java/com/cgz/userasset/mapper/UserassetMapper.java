package com.cgz.userasset.mapper;

import com.cgz.userasset.model.Userasset;
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
 * @since 2020-11-24
 */
public interface UserassetMapper extends BaseMapper<Userasset> {
      /**
        * <p>Description: 搜索</p>
        * @param userasset 
        * @return 全部
      */
      List<Userasset> selectGetAll(@Param("et") Userasset userasset);

      IPage<Userasset> queryPage(Page page, @Param("et") Userasset userasset);

    }
