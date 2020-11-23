package com.cgz.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerAdapter implements WebMvcConfigurer{
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:E:/file/");
        registry.addResourceHandler("/getVerifyCode/**").addResourceLocations("file:E:/file/validataCode/");
    }

   @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new JWTInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/userController/login")
        .excludePathPatterns("/upload/**")
        .excludePathPatterns("/getVerifyCode/**");
    }
	   
	public static void main(String[] args) {
			
	}

}
