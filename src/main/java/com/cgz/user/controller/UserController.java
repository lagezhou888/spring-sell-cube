package com.cgz.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cgz.user.model.User;
import com.cgz.user.service.UserService;
import com.cgz.util.Result;

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
 * @since 2020-10-22
 */
@Controller
@RequestMapping("/userController")
@Api(value="用户接口")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
		
    @ResponseBody
    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ApiOperation(value="登录接口", notes="输入用户名密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "account", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "旧密码", required = true, dataType = "String")
        })
    public Result login(@RequestBody User user){
    	Result result = null;
		User dbUser = userService.getLoginUser(user);
		if(dbUser != null) {
			result = new Result().successOk(dbUser);
			logger.info("登录成功！");
		}else {
			result = new Result().fail(dbUser);
			logger.info("登录失败！");
		}
		return result;
    }
    
    @ResponseBody
    @RequestMapping(value="/modifyPassword",method= RequestMethod.POST)
    @ApiOperation(value="多个入参测试接口", notes="输入用户名密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "password", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", required = true, dataType = "String")
        })
    public String modifyPassword(@RequestParam(value="userId") Long userId, @RequestParam(value="password") String password,
                                 @RequestParam(value="newPassword") String newPassword){
        if(userId <= 0 || userId > 2){
            return "未知的用户";
        }
        if(StringUtils.isEmpty(password) || StringUtils.isEmpty(newPassword)){
            return "密码不能为空";
        }
        if(password.equals(newPassword)){
            return "新旧密码不能相同";
        }
        return "密码修改成功!";
    }
}
