package com.y4j.final_project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.user.service.UserService;


@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String intro(Model model) {
		
		return "y4j_intro";
	}
	
	@GetMapping("/layout")
	public String layout(Model model) {
	
		return "layout/y4j_layout";
	}

	@GetMapping("/index")
	public String index() {
		
		return "index";
	}

	@GetMapping("/popup")
	public String popup() {
		
		return "y4j_popup";
	}
	
	@GetMapping("/admin/hold")
	public String hold() {
		return "admin/hold";
	}
	
	@GetMapping("/testHome")
	public String testHome() {
		return "testHome";
	}
	
	
	@GetMapping("/message")
	public String message() {
		
		
		
		return "message";
	}
	
	
	
}
