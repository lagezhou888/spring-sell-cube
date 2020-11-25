package com.cgz.userasset.controller;


import com.cgz.userasset.model.Userasset;
import com.cgz.userasset.service.IUserassetService;
import com.cgz.userasset.service.impl.UserassetServiceImpl;
import com.cgz.user.controller.UserController;
import com.cgz.userasset.mapper.UserassetMapper;
import com.cgz.util.Result;

import cn.hutool.core.convert.Convert;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/userasset")
@Api(value = "/userasset", description = "接口")
public class UserassetController {

	Logger logger = LoggerFactory.getLogger(UserController.class); 

    /**
        * <p>
        * Description: 服务
        * </p>
    */
     @Autowired
     private UserassetServiceImpl userassetServiceImpl;

     @Autowired
     private IUserassetService userassetService;



        /**
        * <p>
        * Description: 分页查询
        * </p>
        */
      @ApiOperation(value = "分页查询接口", notes = "分页接口")
      @GetMapping(value = "/listPage")
      public Result queryPageList( Userasset userasset,
                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                HttpServletRequest req) {
                Result result = new Result();
                Page<Userasset> page = new Page<Userasset>(pageNo, pageSize);
                            //自定义getAll接口
                IPage<Userasset > pageList=this.userassetServiceImpl.queryPage(page,userasset);
                return result.successOk(pageList);
     }


      /**
      * <p>
      * Description: 根据userId查询数
      * </p>
      */
    @ApiOperation(value = "分页查询接口", notes = "分页接口")
    @GetMapping(value = "/{userId}")
    public Result queryPageList( @PathVariable(value="userId") String userId,HttpServletRequest req) {
      Result result = new Result();
      List<Userasset> userassetList = userassetService.selectByUserId(userId);
      return result.successOk(userassetList);
   }



    /**
    *
    * <p>
    * Description: 添加
    * </p>
    *
    * @param userasset 要添加的，必须包含id
    * @return Result 添加是否成功
    */
     @SuppressWarnings("unchecked")
	 @ApiOperation(value = "新增接口", notes = "新增接口")
     @PostMapping()
     public Result insert(@RequestBody Map<String,Object> model) {
         Result result = new Result();
         Integer userId = null;
         ArrayList<Map<String, Object>> data = null;
         if(model.containsKey("userId")) {
        	userId = Convert.toInt(model.get("userId"),null);
        	//先删除用户相关的数据
//        	LambdaQueryWrapper<Userasset> queryWrapper = Wrappers.<Userasset>lambdaQuery();
//        	queryWrapper.eq(Userasset::getUserId,userId);
//        	userassetService.remove(queryWrapper);
        	
        	Map<String,Object> columnMap = new HashMap<>();
        	columnMap.put("userId", userId);
        	boolean rows = userassetService.removeByMap(columnMap);
         }
         if(model.containsKey("data")) {
        	 int iuserId = userId;
        	 ArrayList<Userasset> userassetList = new ArrayList<Userasset>();
        	 data = (ArrayList<Map<String,Object>>) model.get("data");
        	 data.stream().forEach(item -> {
        		 String isChecked = (boolean) item.get("isChecked") ? "1" : "0";
        		 String name = item.get("name").toString();
        		 ArrayList<Map<String,Object>> lists = (ArrayList<Map<String, Object>>) item.get("list");
        		 lists.stream().forEach(list -> {
        			 Userasset userasset = new Userasset();
        			 userasset.setIsChecked(isChecked);
        			 userasset.setName(name);
        			 userasset.setSelectId(Convert.toInt(list.get("selectId")));
        			 userasset.setSelectName(list.get("selectName").toString());
        			 userasset.setUserId(iuserId);
        			 userassetList.add(userasset);
        		 });
        	 });
        	 logger.info(JSON.toJSONString(userassetList));
        	 boolean isOk = userassetService.saveBatch(userassetList);
	         if (isOk) {
	        	 return result.successOk(isOk);
			 } else {
				 return result.fail(isOk);
			 }
         }
         return result;
     }


    /**
    *
    * <p>
    * Description: 修改
    * </p>
    *
    * @param userasset 要修改的，必须包含id
    * @return Result 修改是否成功
    */
    @ApiOperation(value = "修改接口", notes = "修改接口")
    @PutMapping()
    public Result update(@RequestBody Userasset userasset) {
        Result result = new Result();
        Boolean isOk = this.userassetServiceImpl.updateById(userasset);
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
         Boolean isOk = this.userassetServiceImpl.removeById(id);
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
                 Boolean isOk = this.userassetServiceImpl.removeByIds(Arrays.asList(ids.split(",")));
                  if (!isOk) {
                     return result.successOk(isOk);
                  } else {
                      return result.successOk(isOk);
            }
         }
    }

    }
