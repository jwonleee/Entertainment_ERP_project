package com.y4j.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	
	
	@GetMapping("/user_login")
	public String admin_login() {
		
		return "user/user_login";
	}

	@GetMapping("/user_join")
	public String admin_join() {
		
		return "user/user_join";
	}
	
}
