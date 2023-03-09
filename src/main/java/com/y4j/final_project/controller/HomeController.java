package com.y4j.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.MessageVO;
import com.y4j.final_project.message.service.MessageService;
import com.y4j.final_project.user.service.UserService;


@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
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
	
	@PostMapping("/sendMsgForm")
	public String sendMsgForm(MessageVO vo) {
		
		AdminVO writerVO = adminService.getAdminInfo2(vo.getMsg_writer_id());
		AdminVO receiverVO = adminService.getAdminInfo2(vo.getMsg_receiver_id());
		MessageVO msgVO = MessageVO.builder()
						  .msg_writer_no(writerVO.getAdmin_no())
						  .msg_writer_id(writerVO.getAdmin_id())
						  .msg_writer_name(writerVO.getAdmin_name())
						  .msg_receiver_no(receiverVO.getAdmin_no())
						  .msg_receiver_id(receiverVO.getAdmin_id())
						  .msg_receiver_name(receiverVO.getAdmin_name())
						  .msg_title(vo.getMsg_title())
						  .msg_content(vo.getMsg_content())
						  .build();
		
		messageService.sendMsg(msgVO);
		
		return "redirect:/message";
	}
	
	
	
}
