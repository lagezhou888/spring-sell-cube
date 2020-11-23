package com.cgz.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cgz.conf.JWTUtil;
import com.cgz.user.model.User;
import com.cgz.user.service.UserService;
import com.cgz.util.Result;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.server.HttpServerResponse;
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
	
	@Value("${server.servlet.context-path}")
	private String contextPath;
		
    @ResponseBody
    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ApiOperation(value="登录接口", notes="输入用户名密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "account", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "旧密码", required = true, dataType = "String")
        })
    public Result login(@RequestBody User user,HttpServletResponse response ) throws Exception{
    	Result result = null;
		//将登录信息存入redis
    	String templateValue = redisTemplate.opsForValue().get(user.getAccount());
		if(StringUtils.isEmpty(templateValue)) {
			User dbUser = userService.getLoginUser(user);
			if(dbUser != null) {
				String value = JSON.toJSONString(dbUser);
				redisTemplate.opsForValue().set(user.getAccount(), value);
		        Map<String, String> map = new HashMap<>();
		        map.put("userId", dbUser.getId().toString());
		        String token = JWTUtil.getToken(map);//颁发token
		        // DecodedJWT verify = JWTUtil.verify(token);//解析token
		        logger.info("token认证：" + token);
		        Cookie cookie = new Cookie("token", token);       
		        cookie.setPath("/");         
		        response.addCookie(cookie);
		        response.addHeader("token", token);
				result = new Result().successOk(dbUser);
			}else {
				result = new Result().fail(dbUser);
			}
		}else {
			String userInfo = redisTemplate.opsForValue().get(user.getAccount());
			JSONObject userJson = JSON.parseObject(userInfo);
	        Map<String, String> map = new HashMap<>();
	        map.put("userId", userJson.getString("id"));
	        String token = JWTUtil.getToken(map);//颁发token
	        logger.info("token认证：" + token);
	        // DecodedJWT verify = JWTUtil.verify(token);//解析token
	        // logger.info(verify.getClaim("userId").asString());
	                        
	        Cookie cookie = new Cookie("token", token);       
	        cookie.setPath("/");         
	        response.addCookie(cookie);
	        response.addHeader("token", token);
			result = new Result().successOk(userJson);
		}
		logger.info("登录成功！");
		return result;
    }
    
    @ResponseBody
    @RequestMapping(value="/register",method= RequestMethod.POST)
    @ApiOperation(value="注册接口", notes="输入用户名密码")
    public Result register(@RequestBody User user){
    	Result result = null;
    	user.setName("您还没有昵称哦！");
		boolean isOk = userService.save(user);
		if(isOk) {
			String value = JSON.toJSONString(user);
			redisTemplate.opsForValue().set(user.getAccount(), value);
			result = new Result().successOk(user);
		}else {
			result = new Result().fail(user);
		}
		logger.info("注册成功！");
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
    public Result getVerifyCode(@RequestParam(value = "url") String url){
    	Result result = null;
    	String code = "";
    	code = lineCaptchaCode();
    	HashMap<String,String> map = new HashMap<String,String>();
    	map.put("code",code);
    	map.put("url", url + contextPath + "/getVerifyCode/line.png?time="+System.currentTimeMillis());
		result = new Result().successOk(map);
		logger.info("验证码获取成功！");
		return result;
    }
    
    private String lineCaptchaCode() {
    	//定义图形验证码的长和宽
    	LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
    	//图形验证码写出，可以写出到文件，也可以写出到流
    	lineCaptcha.write("E:\\file\\validataCode\\line.png");
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
