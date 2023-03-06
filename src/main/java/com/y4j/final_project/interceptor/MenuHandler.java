package com.y4j.final_project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MenuHandler implements HandlerInterceptor {

	//핸들러 안에서도 mapper 에 대한 접근이 가능하므로 원하는 정보를 활용하여 제약을 걸어줄 수 있다.
//	@Autowired
//	private ProductMapper productMapper;
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String uri = request.getRequestURI();
		request.setAttribute("uri", uri);
	}
	
}
