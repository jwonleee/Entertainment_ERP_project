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
		
//		registry.addInterceptor(userAuthHandler())
//		.addPathPatterns("/main")
//		.excludePathPatterns("/product/*")
//		.excludePathPatterns("/user/*")
//		.excludePathPatterns("/user/login")
//		.excludePathPatterns("/user/join");

//		registry.addInterceptor(userAuthHandler())
//		.addPathPatterns("/**")
//		.excludePathPatterns("/user/login")
//		.excludePathPatterns("/user/join")
//		.excludePathPatterns("/js/*")
//		.excludePathPatterns("/css/*")
//		.excludePathPatterns("/img/*");
		//REST API 패스에서 제외도 고려해야함
		
		
//		registry.addInterceptor(userAuthHandler())
//		.addPathPatterns("/**")
//		.excludePathPatterns("/user/user_login")
//		.excludePathPatterns("/user/user_join")
//		.excludePathPatterns("/entertainment/ent_list")
//		.excludePathPatterns("/product/product_list")
//		.excludePathPatterns("/user/kakao")
//		.excludePathPatterns("/js/*")
//		.excludePathPatterns("/css/*");
		
		
		registry.addInterceptor(menuHandler())
				.addPathPatterns("/authority/*", "/audition/*", "/schedule/*", "/order/*");
		
		
		
	}
	
	
	
}
