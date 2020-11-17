package com.cgz.attachment.controller;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Value("${server.servlet.context-path}")
	private String contextPath;
	
    @ResponseBody
    @ApiOperation(value="查询", notes="根据userId删除图片")
    @DeleteMapping()
	public Result delete(@RequestParam(value = "userId") Integer userId) {
    	Result result = null;
    	Boolean isOk = attachmentService.deleteByUserId(userId);
    	if(isOk) {
    		result = new Result().successOk(isOk);
			logger.info("删除成功");
    	}else {
    		result = new Result().fail(isOk);
			logger.info("删除失败！");
    	}
    	return result;
	}
	
    @ResponseBody
    @RequestMapping(value="/select/{userId}",method= RequestMethod.GET)
    @ApiOperation(value="查询", notes="根据userId查询图片")
	public Result select(@PathVariable("userId") Integer userId) {
    	Result result = null;
    	String url = attachmentService.getByUserId(userId);
    	if(url != null) {
    		result = new Result().successOk(url);
			logger.info("上传成功！");
    	}else {
    		result = new Result().fail(url);
			logger.info("上传成功！");
    	}
    	return result;
	}
		
    @ResponseBody
    @RequestMapping(value="/upload",method= RequestMethod.POST)
    @ApiOperation(value="上传", notes="上传文件")
    public Result upload(@RequestParam(value = "file") MultipartFile file,
    		@RequestParam(value = "url") String url,
    		@RequestParam(value = "userId") Integer userId,
    		HttpServletRequest request) {
    	Result result = null;
        if (file.isEmpty()) {
        	logger.info("文件为空");
        }
        
//        String path = request.getRequestURL().toString();
//        path = path.substring(0, path.indexOf(request.getRequestURI()));

        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "E://file//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            Attachment attachment = new Attachment();
            String imgUrl = url + contextPath + "/upload/" + fileName;
            attachment.setUrl(imgUrl);
            attachment.setName(fileName);
            attachment.setUserId(userId);
            Attachment attachment_return = attachmentService.upload(attachment);
            if(attachment_return != null) {
            	result = new Result().successOk(attachment_return.getUrl());
    			logger.info("上传成功！");
            }else {
            	result = new Result().fail("上传失败");
    			logger.info("上传失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result;
    }
}
