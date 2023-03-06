package com.y4j.final_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.y4j.final_project.interceptor.MenuHandler;
import com.y4j.final_project.interceptor.UserAuthHandler;



@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	
	@Bean
	public MenuHandler menuHandler() {
		return new MenuHandler();
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*
		registry.addInterceptor(userAuthHandler())
		//		.addPathPatterns(Arrays.asList("경로", "경로", "경로"))
		//		.addPathPatterns("/*")  //경로에 포함
		//		.excludePathPatterns("/user/login", "/user/join", "/js/*", "/css/*", "/img/*");  //경로에서 제외
				//REST API도 제외 path에서 제외해줄 지 결정해야 됨
				.addPathPatterns("/product/*", "/user/*", "/main")
				.excludePathPatterns("/user/login", "/user/join");
		*/
		
		registry.addInterceptor(menuHandler())
				.addPathPatterns("/authority/*", "/audition/*");
		
	}
	
}
