package com.y4j.final_project.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.authority.service.AuthorityService;
import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.MessageVO;
import com.y4j.final_project.message.service.MessageService;
import com.y4j.final_project.user.service.UserService;

@RestController
public class AjaxController {
	
	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	MessageService messageService;
	
	
	@PostMapping("/getAdminInfo")
	public AdminVO getAdminInfo(@RequestBody AdminVO vo) {
		
		return adminService.getAdminInfo(vo.getAdmin_no());
	}
	
//	@PostMapping("/adminUpdateForm")
//	public String updateAdminAuthority(AdminVO vo) {
//		
//		System.out.println(vo);
//		int result = adminService.updateAdminAuthority(vo); 
//		System.out.println("어드민 업데이트 폼 결과 : " + result);
//		
//		return "성공적으로 권한이 수정되었습니다.";
//	}
	
	//수신 메세지 목록 조회
	@PostMapping("/getReceivedMsg")
	public ArrayList<MessageVO> getReceivedMsg(@RequestBody MessageVO vo) {
		
		return messageService.getReceivedMsg(vo.getMsg_receiver_id());
	}
	
	//발신 메세지 목록 조회	
	@PostMapping("/getSentMsg")
	public ArrayList<MessageVO> getSentMsg(@RequestBody MessageVO vo) {
		
		return messageService.getSentMsg(vo.getMsg_writer_id());
	}
	
	//메세지 수신 날짜 업데이트
	@PostMapping("/checkMsg")
	public int checkMsg(@RequestBody MessageVO vo,
			HttpSession session, Model model) {
		
		messageService.checkMsg(vo.getMsg_no());
		
		session.setAttribute("user_id", "manager127");
		
		return messageService.getUncheckedMsg(session.getAttribute("user_id"));
	}
	
	//메세지 수신 날짜 업데이트
	@PostMapping("/getMsgInfo")
	public MessageVO getMsgInfo(@RequestBody MessageVO vo,
			HttpSession session, Model model) {
		
//		session.setAttribute("user_id", "manager127");
		
		return messageService.getMsgInfo(vo.getMsg_no());
	}
	
	
	
}
