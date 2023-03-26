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
	public String message(HttpSession session, Model model) {

		if(session.getAttribute("user_id") != null) {
			model.addAttribute("uncheckedMsgNum", messageService.getUncheckedMsg(session.getAttribute("user_id")));
		
		} else if(session.getAttribute("admin_id") != null) {
			model.addAttribute("uncheckedMsgNum", messageService.getUncheckedMsg(session.getAttribute("admin_id")));
		}
			
		return "message";
	}
	
	@PostMapping("/sendMsgForm")
	public String sendMsgForm(MessageVO vo, RedirectAttributes ra) {
		
		MessageVO msgVO = null;
		
		if(vo.getMsg_writer_type().equals("user") && vo.getMsg_receiver_type().equals("user")) {
			UserVO writerVO = userService.getUserInfo2(vo.getMsg_writer_id());
			UserVO receiverVO = userService.getUserInfo2(vo.getMsg_receiver_id());
			
			msgVO = MessageVO.builder()
					  .msg_writer_no(writerVO.getUser_no())
					  .msg_writer_id(writerVO.getUser_id())
					  .msg_writer_type(vo.getMsg_writer_type())
					  .msg_writer_name(writerVO.getUser_name())
					  .msg_receiver_no(receiverVO.getUser_no())
					  .msg_receiver_id(receiverVO.getUser_id())
					  .msg_receiver_type(vo.getMsg_receiver_type())
					  .msg_receiver_name(receiverVO.getUser_name())
					  .msg_title(vo.getMsg_title())
					  .msg_content(vo.getMsg_content())
					  .build();
			
		} else if(vo.getMsg_writer_type().equals("user") && vo.getMsg_receiver_type().equals("admin")) {
			UserVO writerVO = userService.getUserInfo2(vo.getMsg_writer_id());
			AdminVO receiverVO = adminService.getAdminInfo2(vo.getMsg_receiver_id());
			
			msgVO = MessageVO.builder()
					  .msg_writer_no(writerVO.getUser_no())
					  .msg_writer_id(writerVO.getUser_id())
					  .msg_writer_type(vo.getMsg_writer_type())
					  .msg_writer_name(writerVO.getUser_name())
					  .msg_receiver_no(receiverVO.getAdmin_no())
					  .msg_receiver_id(receiverVO.getAdmin_id())
					  .msg_receiver_type(vo.getMsg_receiver_type())
					  .msg_receiver_name(receiverVO.getAdmin_name())
					  .msg_title(vo.getMsg_title())
					  .msg_content(vo.getMsg_content())
					  .build();
			
		} else if(vo.getMsg_writer_type().equals("admin") && vo.getMsg_receiver_type().equals("user")) {
			AdminVO writerVO = adminService.getAdminInfo2(vo.getMsg_writer_id());
			UserVO receiverVO = userService.getUserInfo2(vo.getMsg_receiver_id());
			
			msgVO = MessageVO.builder()
					  .msg_writer_no(writerVO.getAdmin_no())
					  .msg_writer_id(writerVO.getAdmin_id())
					  .msg_writer_type(vo.getMsg_writer_type())
					  .msg_writer_name(writerVO.getAdmin_name())
					  .msg_receiver_no(receiverVO.getUser_no())
					  .msg_receiver_id(receiverVO.getUser_id())
					  .msg_receiver_type(vo.getMsg_receiver_type())
					  .msg_receiver_name(receiverVO.getUser_name())
					  .msg_title(vo.getMsg_title())
					  .msg_content(vo.getMsg_content())
					  .build();
			
		} else if(vo.getMsg_writer_type().equals("admin") && vo.getMsg_receiver_type().equals("admin")) {
			AdminVO writerVO = adminService.getAdminInfo2(vo.getMsg_writer_id());
			AdminVO receiverVO = adminService.getAdminInfo2(vo.getMsg_receiver_id());
			
			msgVO = MessageVO.builder()
					  .msg_writer_no(writerVO.getAdmin_no())
					  .msg_writer_id(writerVO.getAdmin_id())
					  .msg_writer_type(vo.getMsg_writer_type())
					  .msg_writer_name(writerVO.getAdmin_name())
					  .msg_receiver_no(receiverVO.getAdmin_no())
					  .msg_receiver_id(receiverVO.getAdmin_id())
					  .msg_receiver_type(vo.getMsg_receiver_type())
					  .msg_receiver_name(receiverVO.getAdmin_name())
					  .msg_title(vo.getMsg_title())
					  .msg_content(vo.getMsg_content())
					  .build();
		}
		
		int result = messageService.sendMsg(msgVO);
		String msg = (result == 1) ? "정상적으로 발송 처리되었습니다." : "쪽지 발송에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/message";
	}
	
}
