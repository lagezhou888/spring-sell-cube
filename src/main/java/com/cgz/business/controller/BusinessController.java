package com.cgz.business.controller;


import com.cgz.business.model.Business;
import com.cgz.business.service.impl.BusinessServiceImpl;
import com.cgz.business.mapper.BusinessMapper;
import com.cgz.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caogzh
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/business")
@Api(value = "/business", description = "接口")
public class BusinessController {



    /**dd
        * <p>
        * Description: 服务
        * </p>
    */
     @Autowired
     private BusinessServiceImpl businessServiceImpl;




        /**
        * <p>
        * Description: 分页查询
        * </p>
        */
      @ApiOperation(value = "分页查询接口", notes = "分页接口")
      @RequestMapping(value = "/listPage", method = RequestMethod.POST)
      @ResponseBody
      public Result queryPageList(@RequestBody Business business,
                @RequestParam(name="pageNum", defaultValue="1") Integer pageNum,
                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                HttpServletRequest req) {
                Result result = new Result();
                Page<Business> page = new Page<Business>(pageNum, pageSize);
                            //自定义getAll接口
                IPage<Business > pageList=this.businessServiceImpl.queryPage(page,business);
                 return result.successOk(pageList);
     }






    /**
    *
    * <p>
    * Description: 添加
    * </p>
    *
    * @param business 要添加的，必须包含id
    * @return Result 添加是否成功
    */
     @ApiOperation(value = "新增接口", notes = "新增接口")
     @PostMapping()
     public Result insert(@RequestBody Business business) {
         Result result = new Result();
         Boolean isOk = this.businessServiceImpl.save(business);
         if (!isOk) {
              return result.successOk(business);
         } else {
              return result.fail(business);
         }
     }


    /**
    *
    * <p>
    * Description: 修改
    * </p>
    *
    * @param business 要修改的，必须包含id
    * @return Result 修改是否成功
    */
    @ApiOperation(value = "修改接口", notes = "修改接口")
    @PutMapping()
    public Result update(@RequestBody Business business) {
        Result result = new Result();
        Boolean isOk = this.businessServiceImpl.updateById(business);
        if (!isOk) {
             return result.successOk(isOk);
        } else {
             return result.successOk(isOk);
        }
    }


    /**
    *
    * <p>
    * Description: 删除
    * </p>
    *
    * @param id 根据id删除
    * @return Result 是否删除成功
    */
    @ApiOperation(value = "删除接口", notes = "删除接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
         Result result = new Result();
         Boolean isOk = this.businessServiceImpl.removeById(id);
        if (!isOk) {
             return result.successOk(isOk);
        } else {
              return result.successOk(isOk);
        }
    }

    /**
    *
    * <p>
    * Description: 批量删除
    * </p>
    *
    * @param id 根据ids删除
    * @return Result 是否删除成功
    */
    @ApiOperation(value = "批量删除接口", notes = "批量删除接口")
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
            Result result = new Result();
            if (ids == null || "".equals(ids.trim())) {
                result.successOk("id不能为空");
                return result;
            } else {
                 Boolean isOk = this.businessServiceImpl.removeByIds(Arrays.asList(ids.split(",")));
                  if (!isOk) {
                     return result.successOk(isOk);
                  } else {
                      return result.successOk(isOk);
            }
         }
    }

    }
