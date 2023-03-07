package com.y4j.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.authority.service.AuthorityService;
import com.y4j.final_project.command.AdminVO;

import oracle.jdbc.proxy.annotation.Post;

@RestController
public class AjaxController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	AuthorityService authorityService;
	
	
	@PostMapping("/getAdminInfo")
	public AdminVO getAdminInfo(@RequestBody AdminVO vo) {
		
		return adminService.getAdminInfo(vo.getAdmin_no());
	}
	
//	@PostMapping("/adminUpdateForm")
//	public String updateAdminAuthority(AdminVO vo) {
//		
//		System.out.println(vo);
////		int result = adminService.updateAdminAuthority(vo.getAdmin_no()); 
////		System.out.println("어드민 업데이트 폼 결과 : " + result);
//		
//		return "성공적으로 권한이 수정되었습니다.";
//	}
	
}
