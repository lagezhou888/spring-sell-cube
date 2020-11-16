package com.cgz.config.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgz.config.model.Config;
import com.cgz.config.service.IConfigService;
import com.cgz.user.controller.UserController;
import com.cgz.user.model.User;
import com.cgz.user.service.UserService;
import com.cgz.util.Result;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caogzh
 * @since 2020-11-13
 */
@RestController
@RequestMapping("/config")
@Api(value="配置接口")
public class ConfigController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IConfigService configService;
		
    @ResponseBody
    @RequestMapping(value="/select",method= RequestMethod.GET)
    @ApiOperation(value="查询配置接口", notes="查询配置")
    public Result queryConfig(){
    	Result result = null;
		List<Config> config = configService.selectAll();
		if(config != null) {
			long betweenTime = DateUtil.between(config.get(0).getCountDownFinishTime(), DateUtil.date(), DateUnit.MS);
			result = new Result().successOk(betweenTime);
			logger.info("查询成功！");
		}else {
			result = new Result().fail(config);
			logger.info("查询失败！");
		}
		return result;
    }
}
