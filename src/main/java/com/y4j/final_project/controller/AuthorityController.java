package com.y4j.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	
	
	@GetMapping("/admin_authority_list")
	public String admin_authority_list() {
		
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
