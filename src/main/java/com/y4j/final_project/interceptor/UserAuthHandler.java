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
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id == null) {
			response.sendRedirect(request.getContextPath() + "/user/user_login");
			return false;  //컨트롤러 실행(X)
			
		} else {
			return true;  //컨트롤러 실행(O)
		}
	}

}
