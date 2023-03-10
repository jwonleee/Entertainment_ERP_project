package com.y4j.final_project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@PostMapping("/getReceivedMsg")
	public ArrayList<MessageVO> getReceivedMsg(@RequestBody MessageVO vo) {
		
		return messageService.getReceivedMsg(vo.getMsg_receiver_id());
	}
	
	@PostMapping("/getSentMsg")
	public ArrayList<MessageVO> getSentMsg(@RequestBody MessageVO vo) {
		
		return messageService.getSentMsg(vo.getMsg_writer_id());
	}
	
	
	
}
