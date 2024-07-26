package com.eden.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.eden.intercepor.LoginCheckintercepor;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Autowired
	private LoginCheckintercepor loginChenkIntercepor;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("index").setViewName("index");
		registry.addViewController("regist").setViewName("regist");
		registry.addViewController("login").setViewName("login");
		registry.addViewController("emplist").setViewName("emplist");
		registry.addViewController("addEmp").setViewName("addEmp");
		registry.addViewController("updateEmp").setViewName("updateEmp");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginChenkIntercepor)
		.addPathPatterns("/**")
		.excludePathPatterns("/**/.");
	}
	
	
	

}
