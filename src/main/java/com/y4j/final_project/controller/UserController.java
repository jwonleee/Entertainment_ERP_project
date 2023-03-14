package com.y4j.final_project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.user.service.UserService;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.GoogleAPI;
import com.y4j.final_project.util.KakaoAPI;
import com.y4j.final_project.util.NaverAPI;

import java.util.*;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private KakaoAPI kakao;

	@Autowired
	private NaverAPI naver;
	
	@Autowired
	private GoogleAPI google;

	//회원 로그인
	@GetMapping("/user_login")
	public String user_login() {

		return "user/user_login";
	}
	
	@PostMapping("/userLoginForm")
	public String userLoginForm(UserVO vo, Model model) {
		
		return "redirect:/";
	}
	

	//소셜 간편 로그인 - 카카오
	@GetMapping("/kakao")
	public String kakao(@RequestParam("code") String code){

		String token = kakao.getAccessToken(code);

		System.out.println("어세스토큰: " + token);

		Map<String, Object> map = kakao.getUserInfo(token);
		System.out.println("사용자 데이터: " + map.toString());

		return "redirect:/";
	}

	//소셜 간편 로그인 - 네이버
	@GetMapping("/naver")
	public String naver(@RequestParam("code") String code, @RequestParam("state") String state) {

		String token = naver.getAccessToken(code, state);

		System.out.println("어세스토큰: " + token);

		Map<String, Object> map = naver.getUserInfo(token);
		System.out.println("사용자 데이터: " + map.toString());

		return "redirect:/";
	}

	//소셜 간편 로그인 - 구글
	@GetMapping("/google")
	public String google(@RequestParam("code") String code, @RequestParam("state") String state) {
		String token = google.getAccessToken(code, state);

		System.out.println("어세스토큰: " + token);

		Map<String, Object> map = google.getUserInfo(token);
		System.out.println("사용자 데이터: " + map.toString());

		return "redirect:/";
	}

	//회원가입
	@GetMapping("/user_join")
	public String user_join() {

		return "user/user_join";
	}

	@PostMapping("/userJoinForm")
	public String userJoinForm(@Valid UserVO vo, Errors errors, Model model, Criteria cri) {

		if(errors.hasErrors()) { //에러가 있다면 true, 없다면 false

			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
				// System.out.println(err.getField()); //에러 필드명
				// System.out.println(err.getDefaultMessage()); //에러 메시지

				if(err.isBindingFailure()) { //유효성 검사의 실패가 아니라, 자바 내부의 에러라면 true 반환
					model.addAttribute("valid_" + err.getField(), "형식이 올바르지 않습니다");
				} else { //유효성 검사에 실패한 목록
					model.addAttribute("valid_" +err.getField(), err.getDefaultMessage());
				}
			}
			//end
			model.addAttribute("vo", vo); //사용자가 작성한 값을 화면으로 보내기(vo에 사용자가 작성한 값 들어있음)
			return "user/user_join"; //실패하면 원래 화면으로
		}
		String pw = passwordEncoder.encode(vo.getUser_pw());
		vo.setUser_pw(pw);

		//vo.setUser_no(userService.getUserTotal(cri) + 1);

		userService.registUser(vo);
		return "redirect:/user/user_login";
	}
	
	//마이페이지
	@GetMapping("/mypage")
	public String mypage() {
		
		return "user/mypage";
	}
	
	//회원정보수정
	@GetMapping("/user_info_edit")
	public String user_info_edit() {
		
		return "user/user_info_edit";
	}
	
	@PostMapping("/userInfoForm")
	public String userInfoForm(@Valid UserVO vo, Errors errors, Model model, Criteria cri) {
		
		if(errors.hasErrors()) { //에러가 있다면 true, 없다면 false

			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
				// System.out.println(err.getField()); //에러 필드명
				// System.out.println(err.getDefaultMessage()); //에러 메시지

				if(err.isBindingFailure()) { //유효성 검사의 실패가 아니라, 자바 내부의 에러라면 true 반환
					model.addAttribute("valid_" + err.getField(), "형식이 올바르지 않습니다");
				} else { //유효성 검사에 실패한 목록
					model.addAttribute("valid_" +err.getField(), err.getDefaultMessage());
				}
			}
			//end
			model.addAttribute("vo", vo); //사용자가 작성한 값을 화면으로 보내기(vo에 사용자가 작성한 값 들어있음)
			return "user/user_info_edit"; //실패하면 원래 화면으로
		}
		String pw = passwordEncoder.encode(vo.getUser_pw());
		String pw2 = passwordEncoder.encode(vo.getUser_pw2());
		vo.setUser_pw(pw);
		vo.setUser_pw2(pw2);

		//vo.setUser_no(userService.getUserTotal(cri) + 1);

		userService.registUser(vo);
		
		return "redirect:/user/mypage";
	}





}
