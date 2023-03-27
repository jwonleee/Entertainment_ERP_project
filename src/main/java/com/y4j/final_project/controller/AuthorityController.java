package com.y4j.final_project.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.authority.service.AuthorityService;
import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.AuthorityVO;
import com.y4j.final_project.command.MessageVO;
import com.y4j.final_project.message.service.MessageService;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.PageVO;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private MessageService messageService;
	
	
	//관리자 권한 조회
	@GetMapping("/admin_authority_list")
	public String admin_authority_list(Model model,
			HttpSession session, Criteria cri) {
		
		ArrayList<AdminVO> list = adminService.getAdminList(cri);
		model.addAttribute("list", list);
		
		int total = adminService.getAdminTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("pageVO", pageVO);
		
		return "authority/admin_authority_list";
	}
	
	//권한 신청 목록
	@GetMapping("/admin_authority_apply_list")
	public String admin_authority_apply_list(Model model,
			HttpSession session, Criteria cri) {
		
		ArrayList<AuthorityVO> list = authorityService.getAuthorityApplyList(cri);
		model.addAttribute("list", list);
		
		int total = authorityService.getAuthorityApplyTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("pageVO", pageVO);
		
		return "authority/admin_authority_apply_list";
	}
	
	//권한 조회 페이지 모달 창 내 수정
	@PostMapping("/adminAuthUpdateForm")
	public String adminAuthUpdateForm(AdminVO vo, RedirectAttributes ra) {
		
		AdminVO originalVO = adminService.getAdminInfo(vo.getAdmin_no());
		
		int result = adminService.updateAdminAuthority(vo);
		
		String msg = (result == 1) ? "정상적으로 권한이 수정되었습니다." : "권한 수정에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);

		//권한 수정 대상자에게 권한 변경 내역 쪽지 발송
		MessageVO msgVO = MessageVO.builder()
						  .msg_writer_no(6)
						  .msg_writer_id("Administrator")
						  .msg_writer_type("admin")
						  .msg_writer_name("Administrator")
						  .msg_receiver_no(originalVO.getAdmin_no())
						  .msg_receiver_id(originalVO.getAdmin_id())
						  .msg_receiver_type("admin")
						  .msg_receiver_name(originalVO.getAdmin_name())
						  .msg_title("관리자에 의해 권한이 수정되었습니다.")
						  .msg_content("권한 변경 내역 : " + (originalVO.getAdmin_type().equals("manager") ? originalVO.getAdmin_type() + " - " + originalVO.getEnt_name() : originalVO.getAdmin_type())
								  + " → " + (vo.getAdmin_type().equals("manager") ? vo.getAdmin_type() + " - " + vo.getEnt_name() : vo.getAdmin_type()))
						  .build();
		messageService.sendMsg(msgVO);
		
		return "redirect:/authority/admin_authority_list";
	}
	
	
	//권한 신청 승인 처리
	@PostMapping("/approveAuthForm")
	public String approveAuth(AuthorityVO vo,
							  RedirectAttributes ra) {

		AdminVO originalVO = adminService.getAdminInfo(vo.getAuthority_mng_admin_no());
		
		int result1 = authorityService.approveAuth(vo);
		int result2 = adminService.approveAuth(vo);
		
		String msg = (result1 == 1 && result2 == 1) ? "정상적으로 승인 처리되었습니다." : "승인 처리에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		//권한 신청 승인 시, 권한 변경 내역 쪽지 발송
		MessageVO msgVO = MessageVO.builder()
						  .msg_writer_no(6)
						  .msg_writer_id("Administrator")
						  .msg_writer_type("admin")
						  .msg_writer_name("Administrator")
						  .msg_receiver_no(originalVO.getAdmin_no())
						  .msg_receiver_id(originalVO.getAdmin_id())
						  .msg_receiver_type("admin")
						  .msg_receiver_name(originalVO.getAdmin_name())
						  .msg_title("권한 신청이 승인되었습니다.")
						  .msg_content(originalVO.getAdmin_name() + "(" + originalVO.getAdmin_id()
						  	+ ")님의 관리자 유형 : " + (vo.getAuthority_mng_admin_apply_type().equals("manager") ?
						  	vo.getAuthority_mng_admin_apply_type() + " - " + vo.getEnt_name() : vo.getAuthority_mng_admin_apply_type())) 
						  .build();
		messageService.sendMsg(msgVO);

		return "redirect:/authority/admin_authority_apply_list";
	}
	
	//권한 신청 반려 처리
	@PostMapping("/rejectAuthForm")
	public String rejectAuth(AuthorityVO vo,
			  				 RedirectAttributes ra) {
		
		AdminVO originalVO = adminService.getAdminInfo(vo.getAuthority_mng_admin_no());
		
		int result = authorityService.rejectAuth(vo);
		String msg = (result == 1) ? "정상적으로 반려 처리되었습니다." : "반려 처리에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		//권한 신청 승인 시, 권한 변경 내역 쪽지 발송
		MessageVO msgVO = MessageVO.builder()
						  .msg_writer_no(6)
						  .msg_writer_id("Administrator")
						  .msg_writer_type("admin")
						  .msg_writer_name("Administrator")
						  .msg_receiver_no(originalVO.getAdmin_no())
						  .msg_receiver_id(originalVO.getAdmin_id())
						  .msg_receiver_type("admin")
						  .msg_receiver_name(originalVO.getAdmin_name())
						  .msg_title("권한 신청이 반려되었습니다.")
						  .msg_content(originalVO.getAdmin_name() + "(" + originalVO.getAdmin_id()
						  	+ ")님의 관리자 유형 : " + (originalVO.getAdmin_type().equals("none") ? "없음" :
							( originalVO.getAdmin_type().equals("manager") ?
									originalVO.getAdmin_type() + " - " + originalVO.getEnt_name() : originalVO.getAdmin_type() ) ) ) 
						  .build();
		messageService.sendMsg(msgVO);
		
		return "redirect:/authority/admin_authority_apply_list";
	}
	
	
	
}
