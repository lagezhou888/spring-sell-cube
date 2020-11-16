package com.cgz.attachment.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgz.attachment.model.Attachment;
import com.cgz.attachment.service.IAttachmentService;
import com.cgz.user.controller.UserController;
import com.cgz.user.model.User;
import com.cgz.user.service.UserService;
import com.cgz.util.Result;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caogzh
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/attachment")
public class AttachmentController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IAttachmentService attachmentService;
		
    @ResponseBody
    @RequestMapping(value="/upload",method= RequestMethod.POST)
    @ApiOperation(value="上传", notes="上传文件")
    public Result upload(@RequestBody Attachment attachment){
    	Result result = null;
    	Attachment img = attachmentService.upload(attachment);
		if(img != null) {
			result = new Result().successOk(img);
			logger.info("登录成功！");
		}else {
			result = new Result().fail(img);
			logger.info("登录失败！");
		}
		return result;
    }
}
