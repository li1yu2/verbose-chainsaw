package com.eden.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("index").setViewName("index");
		registry.addViewController("regist").setViewName("regist");
		registry.addViewController("login").setViewName("login");
		registry.addViewController("emplist").setViewName("emplist");
		
	}

}
