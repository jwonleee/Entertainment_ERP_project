package com.y4j.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserController {

	
	
	@GetMapping("/admin_login")
	public String admin_login() {
		
		return "admin/admin_login";
	}

	@GetMapping("/admin_join")
	public String admin_join() {
		
		return "admin/admin_join";
	}
}
