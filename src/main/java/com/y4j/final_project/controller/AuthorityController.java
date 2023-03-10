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
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.PageVO;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthorityService authorityService;
	
	
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
	
	@PostMapping("/adminAuthUpdateForm")
	public String adminAuthUpdateForm(AdminVO vo, RedirectAttributes ra) {
		
		int result = adminService.updateAdminAuthority(vo); 
		
		String msg = (result == 1) ? "정상적으로 권한이 수정되었습니다." : "권한 수정에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/authority/admin_authority_list";
	}
	
	
	//권한 신청 승인 처리
	@PostMapping("/approveAuthForm")
	public String approveAuth(@ModelAttribute("AuthorityVO") AuthorityVO vo,
							  RedirectAttributes ra) {
		
		int result1 = authorityService.approveAuth(vo);
		int result2 = adminService.approveAuth(vo);
		
		String msg = (result1 == 1 && result2 == 1) ? "정상적으로 승인 처리되었습니다." : "승인 처리에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/authority/admin_authority_apply_list";
	}
	
	//권한 신청 반려 처리
	@PostMapping("/rejectAuthForm")
	public String rejectAuth(@ModelAttribute("AuthorityVO") AuthorityVO vo,
			  				 RedirectAttributes ra) {
		
		int result = authorityService.rejectAuth(vo);
		String msg = (result == 1) ? "정상적으로 반려 처리되었습니다." : "반려 처리에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/authority/admin_authority_apply_list";
	}
	
	
	
}
