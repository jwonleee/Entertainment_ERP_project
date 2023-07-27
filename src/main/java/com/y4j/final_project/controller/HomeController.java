package com.y4j.final_project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.authority.service.AuthorityService;
import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.MessageVO;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.message.service.MessageService;
import com.y4j.final_project.user.service.UserService;


@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private MessageService messageService;
	
	
	@GetMapping("/")
	public String intro(Model model) {
		return "y4j_intro";
	}
	
	@GetMapping("/layout")
	public String layout(Model model) {
		return "layout/y4j_layout";
	}

	// react build 파일 연결
	@GetMapping({"/tab/*", "/artist/*",
		         "/artist/nj/artistDetail_newjeans/*", "/artist/bp/artistDetail_Bp/*", "/artist/ive/artistDetail_ive/*",
		         "/artist/ldh/artistDetail_ldh/*", "/artist/csb/artistDetail_csb/*"
				})
	public String about() {
		return "index";
	}
	
	@GetMapping("/popup")
	public String popup() {
		return "y4j_popup";
	}
	
	@GetMapping("/testHome")
	public String testHome() {
		return "testHome";
	}
	
}
