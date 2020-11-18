package com.cgz.user.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cgz.user.model.User;
import com.cgz.user.service.UserService;
import com.cgz.util.Result;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
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
	
	@Autowired
	private StringRedisTemplate redisTemplate;
		
    @ResponseBody
    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ApiOperation(value="登录接口", notes="输入用户名密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "account", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "旧密码", required = true, dataType = "String")
        })
    public Result login(@RequestBody User user){
    	Result result = null;
    	
		//将登录信息存入redis
		String templateValue = redisTemplate.opsForValue().get("userinfo");
		if(StringUtils.isEmpty(templateValue)) {
			User dbUser = userService.getLoginUser(user);
			if(dbUser != null) {
				String value = JSON.toJSONString(dbUser);
				redisTemplate.opsForValue().set("userinfo", value);
				result = new Result().successOk(dbUser);
			}else {
				result = new Result().fail(dbUser);
			}
		}else {
			String userInfo = redisTemplate.opsForValue().get("userinfo");
			JSONObject userJson = JSON.parseObject(userInfo);
			result = new Result().successOk(userJson);
		}
		logger.info("登录成功！");
		return result;
    }
    
    @ResponseBody
    @RequestMapping(value="/modify",method= RequestMethod.PUT)
    @ApiOperation(value="修改用户名", notes="输入用户名")
    public Result modify(@RequestBody User user) {
    	Result result = null;
    	Boolean isOk = userService.updateById(user);
    	if(isOk) {
    		result = new Result().successOk(isOk);
    	}else {
    		result = new Result().fail(isOk);
    	}
    	return result;
    }
    
    @ResponseBody
    @RequestMapping(value="/getVerifyCode",method= RequestMethod.GET)
    @ApiOperation(value="登录接口", notes="获取验证码")
    public Result getVerifyCode(){
    	Result result = null;
    	String code = "";
    	code = lineCaptchaCode();
		result = new Result().successOk(code);
		logger.info("验证码获取成功！");
		return result;
    }
    
    private String lineCaptchaCode() {
    	//定义图形验证码的长和宽
    	LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
    	//图形验证码写出，可以写出到文件，也可以写出到流
    	lineCaptcha.write("E:\\chineseGongfu\\cli4-vue2-springboot\\src\\assets\\validateCode\\line.png");
    	//输出code
    	return lineCaptcha.getCode();
    	//验证图形验证码的有效性，返回boolean值
//    	lineCaptcha.verify("1234");

//    	//重新生成验证码
//    	lineCaptcha.createCode();
//    	lineCaptcha.write("E:\\\\chineseGongfu\\\\cli4-vue2-springboot\\\\validateCode\\\\line.png");
//    	//新的验证码
//    	logger.info(lineCaptcha.getCode());
//    	//验证图形验证码的有效性，返回boolean值
//    	lineCaptcha.verify("1234");
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
