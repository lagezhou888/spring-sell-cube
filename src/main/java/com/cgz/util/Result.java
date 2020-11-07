package com.cgz.util;

import org.springframework.lang.Nullable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "返回模型",description = "接口返回对象" )
@Data
public class Result  { 
	@ApiModelProperty("状态码") 
	private Integer code = 200; 
	
	@ApiModelProperty("返回消息") 
	private String msg = "操作成功！"; 
	
	@ApiModelProperty("返回数据") 
	private Object data; 
	
	@ApiModelProperty("成功标志") 
	private boolean success = true; 
	
	private String messageCode = "成功"; 
	
	public Result successOk(@Nullable Object d) {
		this.messageCode = "成功"; 
		this.code = 200; 
		this.success = true; 
		this.data = d; 
		return this;
   }
   public Result fail(@Nullable Object d) {
		this.messageCode = "失败"; 
		this.code = 500; 
		this.success = false; 
		this.data = d; 
		this.msg = "操作失败";
		return this;
   }
   public String toString() { 
		return "Result(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ", success=" + this.isSuccess()  + ", messageCode=" + this.getMessageCode() + ", customizedMessage=" + ")";
   } 
   public Result(Integer code, String msg, Object data, boolean success, String messageCode) { 
		this.code = code; 
		this.msg = msg; 
		this.data = data; 
		this.success = success; 
		this.messageCode = messageCode;
   }
   public Result() {

   }
}