package com.y4j.final_project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminAuthHandler implements HandlerInterceptor {

	//user_id 세션 없을 경우 특정 페이지 블럭
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String admin_id = (String)session.getAttribute("admin_id");
		
		if(admin_id == null) {
			response.sendRedirect(request.getContextPath() + "/admin/admin_login");
			return false;  //컨트롤러 실행(X)
			
		} else {
			return true;  //컨트롤러 실행(O)
		}
	}

}
