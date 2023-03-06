package com.y4j.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.authority.service.AuthorityService;
import com.y4j.final_project.command.AdminVO;

@RestController
public class AjaxController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	AuthorityService authorityService;
	
	
	@PostMapping("/getAdminInfo")
	public AdminVO getAdminInfo(@RequestBody AdminVO vo) {
		
		return  adminService.getAdminInfo(vo.getAdmin_no());
	}
	
	
	
}
