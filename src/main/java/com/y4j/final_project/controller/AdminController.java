package com.y4j.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.authority.service.AuthorityService;
import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.AuthorityVO;
import com.y4j.final_project.util.Criteria;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthorityService authorityService;
	
	
	@GetMapping("/admin_login")
	public String admin_login() {
		
		return "admin/admin_login";
	}

	@GetMapping("/admin_join")
	public String admin_join() {
		
		return "admin/admin_join";
	}
	
	@GetMapping("/admin_join2")
	public String admin_join2() {
		
		return "admin/admin_join2";
	}
	
	@GetMapping("/admin_home")
	public String admin_home() {
		
		return "admin/admin_home";
	}
	
	@PostMapping("/adminJoinForm")
	public String adminJoinForm(AdminVO vo1,Criteria cri,
								RedirectAttributes ra) {
		//관리자 회원가입
		vo1.setAdmin_no(adminService.getAdminTotal(cri) + 1);
		System.out.println("AdminVO Total : " + vo1.getAdmin_no());
		//권한 신청
		AuthorityVO vo2 = AuthorityVO.builder()
						  .authority_mng_no(authorityService.getAuthorityApplyTotal(cri) + 1)
						  .authority_mng_admin_no(vo1.getAdmin_no())
						  .authority_mng_admin_id(vo1.getAdmin_id())
						  .authority_mng_admin_name(vo1.getAdmin_name())
						  .authority_mng_admin_apply_type(vo1.getAdmin_type())
						  .ent_name(vo1.getEnt_name())
						  .build();
		
		int result1 = adminService.registAdmin(vo1);
		int result2 = authorityService.applyAuthority(vo2);
		
		String msg = (result1 == 1 && result2 == 1) ? "정상적으로 회원가입 되었습니다.\n관리자 권한 승인을 기다려주세요." : "회원가입에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/admin/admin_login";
	}
	
}
