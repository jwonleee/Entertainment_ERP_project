package com.y4j.final_project.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.authority.service.AuthorityService;
import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.PageVO;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/admin_authority_list")
	public String admin_authority_list(Model model,
			HttpSession session, Criteria cri) {
		
		ArrayList<AdminVO> list = adminService.getAdminList(cri);
		model.addAttribute("list", list);
		
		int total = adminService.getAdminTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("pageVO", pageVO);
		
		return "authority/admin_authority_list";
	}
	
	@GetMapping("/admin_authority_apply_list")
	public String admin_authority_apply_list() {
		
		return "authority/admin_authority_apply_list";
	}
	
	@GetMapping("/admin_authority_register")
	public String admin_authority_register() {
		
		return "authority/admin_authority_register";
	}
	
	
	
}
