package com.y4j.final_project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserAuthHandler implements HandlerInterceptor {

	//user_id 세션 없을 경우 특정 페이지 블럭
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("UserAuthHandler - preHandle Execute!");
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id == null) {
			System.out.println("user_id 가 없습니다.");
			response.sendRedirect(request.getContextPath() + "/user/user_login");
			return false;  //컨트롤러 실행(X)
			
		} else {
			System.out.println("user_id : " + user_id);
			return true;  //컨트롤러 실행(O)
		}
	}

//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
	
}
