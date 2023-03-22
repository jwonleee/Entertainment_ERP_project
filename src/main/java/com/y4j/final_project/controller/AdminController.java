package com.y4j.final_project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
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
	public String adminJoinForm(@Valid AdminVO vo1, Errors errors, Model model, Criteria cri,
								RedirectAttributes ra) {
		
		//유효성 검사
		if(errors.hasErrors()) { //에러가 있다면 true, 없다면 false
			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
				if(err.isBindingFailure()) { //유효성 검사의 실패가 아니라, 자바 내부의 에러라면 true 반환
					model.addAttribute("valid_" + err.getField(), "형식이 올바르지 않습니다");
				} else { //유효성 검사에 실패한 목록
					model.addAttribute("valid_" +err.getField(), err.getDefaultMessage());
				}
			}

//			//아이디 중복 검사
//			int result = adminService.idCheck2(vo1.getAdmin_id()); 
//			System.out.println("아이디 중복 검사 결과 : " + result);
//			model.addAttribute("vo1", vo1);

			//비밀번호 암호화
			String pw = passwordEncoder.encode(vo1.getAdmin_pw());
			vo1.setAdmin_pw(pw);
			
			adminService.registAdmin(vo1);
			return "admin/admin_login";
		}
		
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

//		//관리자 회원가입
//		vo1.setAdmin_no(adminService.getAdminTotal(cri) + 1);
//		System.out.println("AdminVO Total : " + vo1.getAdmin_no());
		
		model.addAttribute("vo1", null);
		return "admin/admin_join";
	}
	
	@PostMapping("/adminLoginForm")
	public String adminLoginForm(@RequestParam("admin_id") String admin_id, @RequestParam("admin_pw") String admin_pw, 
			AdminVO vo, HttpServletRequest request, Model model) {

		int count = adminService.idCheck2(admin_id); //db에 저장되어있는 아이디
		String saved_pw = adminService.login(admin_id); //db에 저장되어있는 비밀번호

		//세션이 있으면 세션 반환, 없으면 새로 생성해서 반환
		//getSession()은 기본값이 true인데 false로 설정할 경우, 세션이 없을 때 새로 생성하지 않고 null 반환
		HttpSession session = request.getSession();

		if(count == 1 && passwordEncoder.matches(admin_pw, saved_pw) ) {

			//세션에 회원정보 저장 - 아이디로 가져와서
		AdminVO adminVO = adminService.getAdminInfo2(admin_id);
			session.setAttribute("admin_id", adminVO.getAdmin_id());
			session.setAttribute("admin_type", adminVO.getAdmin_type());
			
			return "user/mypage";
		} else {
			session.setAttribute("admin_id", null);
			return "admin/admin_home";
		}
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied(RedirectAttributes ra) {
		
		ra.addFlashAttribute("msg", "접근 권한이 없는 페이지입니다.");
		
		return "redirect:/admin/admin_home";
	}
	
}
