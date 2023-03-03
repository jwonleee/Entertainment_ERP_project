package com.y4j.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestHomeController {
	
	@RequestMapping("/test")
	public String testhome() {
		return "/testHome";
	}
}
