package com.y4j.final_project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class SaleAuthHandler implements HandlerInterceptor {

	//상품 관리 섹션 접근 권한 처리
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("SaleAuthHandler - preHandle Execute!");
		
		HttpSession session = request.getSession();
		String admin_type = (String)session.getAttribute("admin_type");
		
		if(admin_type.equals("master")) {
			return true;
			
		} else if(!admin_type.equals("sale")) {
			response.sendRedirect(request.getContextPath() + "/admin/admin_home");
			return false;  //컨트롤러 실행(X)
			
		} else {
			return true;  //컨트롤러 실행(O)
		}
	}
	
}
