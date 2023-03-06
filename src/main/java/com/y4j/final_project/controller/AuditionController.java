package com.y4j.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/audition")
public class AuditionController {

	@GetMapping("/admin_audition_list")
	public String admin_audition_list() {
		
		return "audition/admin_audition_list";
	}
	
	@GetMapping("/admin_audition_register")
	public String admin_audition_register() {
		
		return "audition/admin_audition_register";
	}

	
	
}
