package com.y4j.final_project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.audition.service.AuditionService;
import com.y4j.final_project.authority.service.AuthorityService;
import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.AuditionFileVO;
import com.y4j.final_project.command.AuditionVO;
import com.y4j.final_project.command.MessageVO;
import com.y4j.final_project.message.service.MessageService;
import com.y4j.final_project.user.service.UserService;

@RestController
public class AjaxController {

	

	@Autowired
	AdminService adminService;

	@Autowired
	AuthorityService authorityService;

	@Autowired
	MessageService messageService;

	@Autowired
	AuditionService auditionService;
	

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
	
	// 특정 메세지 수신 날짜 업데이트
	@PostMapping("/getMsgInfo")
	public MessageVO getMsgInfo(@RequestBody MessageVO vo) {
		
		return messageService.getMsgInfo(vo.getMsg_no());
	}
	
//	//세션에 저장된 유저 아이디를 통해 정보 가져오기
//	@PostMapping("/getUserInfo2")
//	public UserVO getUserInfo2(@RequestBody UserVO vo,
//			HttpSession session) {
//		
//		session.setAttribute("user_id", "ccc333");
//		
//		return userService.getUserInfo2(session.getAttribute("user_id"));
//	}
	
	
	//특정 지원서 정보 반환
	@PostMapping("/getAudCv")
	public AuditionVO getAudCv(@RequestBody AuditionVO vo) {
		
		return auditionService.getAudCv(vo.getAudition_cv_no());
	}
	
	//특정 지원서 파일 정보 반환
	@PostMapping("/getAudFile")
	public List<AuditionFileVO> getAudFile(@RequestBody AuditionVO vo) {
		
		return auditionService.getAudFile(vo);
	}
	
}
