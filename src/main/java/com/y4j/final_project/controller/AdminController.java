package com.y4j.final_project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.admin.service.AdminService;
import com.y4j.final_project.authority.service.AuthorityService;
import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.AuthorityVO;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.util.Criteria;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthorityService authorityService;
	
	
	@GetMapping("/admin_login")
	public String admin_login(HttpSession session, AdminVO adminVO, Model model) {

		//정상적으로 로그인이 됐을 경우, 세션 생성
		session.setAttribute("adminId", adminVO.getAdmin_id());
		session.setAttribute("adminType", adminVO.getAdmin_type());
		session.setAttribute("entName", adminVO.getEnt_name());
		model.addAttribute("adminId", session.getAttribute("adminId"));
		model.addAttribute("adminType", session.getAttribute("adminType"));
		model.addAttribute("entName", session.getAttribute("entName"));
		
		return "admin/admin_login";
	}

	@GetMapping("/admin_join")
	public String admin_join() {
		
		return "admin/admin_join";
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
	
	@PostMapping("/adminLoginForm")
	public String adminLoginForm(@RequestParam("admin_id") String admin_id, @RequestParam("admin_pw") String admin_pw, 
			AdminVO vo, HttpServletRequest request, Model model) {

//		int count = userService.idCheck(user_id); //db에 저장되어있는 아이디
//		String saved_pw = userService.login(user_id); //db에 저장되어있는 비밀번호

		//세션이 있으면 세션 반환, 없으면 새로 생성해서 반환
		//getSession()은 기본값이 true인데 false로 설정할 경우, 세션이 없을 때 새로 생성하지 않고 null 반환
		HttpSession session = request.getSession();

//		if(count == 1 && passwordEncoder.matches(user_pw, saved_pw) ) {

			//세션에 회원정보 저장 - 아이디로 가져와서
		AdminVO adminVO = adminService.getAdminInfo2(admin_id);
			session.setAttribute("admin_id", adminVO.getAdmin_id());
			session.setAttribute("admin_type", adminVO.getAdmin_type());
			
//			return "user/mypage";
//			
//		} else {
//			session.setAttribute("vo", null);
//			//model.addAttribute("vo", null);
//			return "user/user_login";
//		}
			return "admin/admin_home";
	}
	
}
