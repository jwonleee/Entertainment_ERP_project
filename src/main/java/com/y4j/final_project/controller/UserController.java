package com.y4j.final_project.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.y4j.final_project.command.CartVO;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.email.service.EmailService;
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
	private EmailService emailService;
	
	@Autowired
	private KakaoAPI kakao;

	@Autowired
	private NaverAPI naver;

	@Autowired
	private GoogleAPI google;

	//회원가입
	@GetMapping("/user_join")
	public String user_join() {

		return "user/user_join";
	}

	@PostMapping("/userJoinForm")
	public String userJoinForm(@Valid UserVO vo, Errors errors, Model model, RedirectAttributes ra) {

		if(errors.hasErrors()) { //에러가 있다면 true, 없다면 false
			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
				if(err.isBindingFailure()) { //유효성 검사의 실패가 아니라, 자바 내부의 에러라면 true 반환
					model.addAttribute("valid_" + err.getField(), "형식이 올바르지 않습니다");
				} else { //유효성 검사에 실패한 목록
					model.addAttribute("valid_" +err.getField(), err.getDefaultMessage());
				}
			}
			userService.idCheck(vo.getUser_id()); //아이디 중복 검사

			model.addAttribute("vo", vo); //사용자가 작성한 값을 화면으로 보내기(vo에 사용자가 작성한 값 들어있음)
			String pw = passwordEncoder.encode(vo.getUser_pw());
			vo.setUser_pw(pw);
			userService.registUser(vo);

			return "user/user_login"; 
		}
		model.addAttribute("vo", null);
		
		return "user/user_join"; //실패하면 원래 화면으로
	}
	
	//이메일 인증 - 회원가입&비밀번호 찾기
	@PostMapping("/emailConfirm")
	public String emailConfirm(@RequestParam String email, HttpServletRequest request) throws Exception {
		
	  String confirm = emailService.sendSimpleMessage(email);

	  return confirm;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////

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
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//회원 로그인
	@GetMapping("/user_login")
	public String user_login() {

		return "user/user_login";
	}

	@PostMapping("/userLoginForm")
	public String userLoginForm(@RequestParam("user_id") String user_id, @RequestParam("user_pw") String user_pw, 
			UserVO vo, HttpServletRequest request, Model model) {

		int count = userService.idCheck(user_id); //db에 저장되어있는 아이디
		String saved_pw = userService.login(user_id); //db에 저장되어있는 비밀번호

		//세션이 있으면 세션 반환, 없으면 새로 생성해서 반환
		//getSession()은 기본값이 true인데 false로 설정할 경우, 세션이 없을 때 새로 생성하지 않고 null 반환
		HttpSession session = request.getSession(); 

		if(count == 1 && passwordEncoder.matches(user_pw, saved_pw) ) {

			//세션에 회원정보 저장 - 아이디로 가져와서
			session.setAttribute("vo", userService.getUserInfo2(vo.getUser_id()));
			//session.setAttribute("user_id", user_id);
			//model.addAttribute("vo", vo);
			return "user/mypage";
		} else {
			session.setAttribute("vo", null);
			//model.addAttribute("vo", null);
			return "user/user_login";
		}
	}

	//아이디 찾기
	@GetMapping("user_find_id")
	public String user_find_id() {

		return "user/user_find_id";
	}

	@PostMapping("/userFindIdForm")
	public String userFindIdForm(UserVO vo, Model model) {
		UserVO result = userService.getUserId(vo); //db에 담겨있는 vo값들
		//System.out.println(result);
		
		model.addAttribute("vo", result); //model에 담아서 화면에 뿌리기 
		return "user/user_find_id_result"; //결과페이지로
	}

	//아이디찾기 결과
	@GetMapping("user_find_id_result")
	public String user_find_id_result() {

		return "user/user_find_id_result";
	}

	//비밀번호 찾기
	@GetMapping("user_find_pw")
	public String user_find_pw() {

		return "user/user_find_pw";
	}

	//마이페이지
	@GetMapping("/mypage")
	public String mypage() {

		return "user/mypage";
	}

	//회원정보수정
	@GetMapping("/user_info_edit")
	public String user_info_edit(HttpSession session) {
		
		UserVO vo = (UserVO)session.getAttribute("vo"); //세션에 저장된 회원 정보
		String user_id = (String)session.getAttribute("user_id"); //세션에 저장된 회원 아이디

		return "user/user_info_edit";
	}

	@PostMapping("/userInfoForm")
	public String userInfoForm(@Valid UserVO vo, Errors errors, HttpServletRequest request, Model model, @RequestParam("user_id") String user_id) {

		HttpSession session = request.getSession();
		session.setAttribute("vo", vo);
		int result = userService.updateUserInfo(vo); 
		//System.out.println(result);
		//System.out.println(vo);

		if(errors.hasErrors()) { //에러가 있다면 true, 없다면 false
			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
				if(err.isBindingFailure()) { //유효성 검사의 실패가 아니라, 자바 내부의 에러라면 true 반환
					model.addAttribute("valid_" + err.getField(), "형식이 올바르지 않습니다");
				} else { //유효성 검사에 실패한 목록
					model.addAttribute("valid_" +err.getField(), err.getDefaultMessage());
				}
			}
			model.addAttribute("vo", vo);
			//비밀번호 암호화
			String pw = passwordEncoder.encode(vo.getUser_pw());
			vo.setUser_pw(pw);
			
			userService.updateUserInfo(vo);

			return "user/mypage";
		}
		model.addAttribute("vo", vo); //사용자가 작성한 값을 화면으로 보내기(vo에 사용자가 작성한 값 들어있음)
		return "user/user_info_edit"; //실패하면 원래 화면으로
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@GetMapping("/cart")
	public String cart(HttpSession session, Model model) {
		
		//세션에 저장되어 있는 유저 아이디 가져오기
				UserVO vo = (UserVO)session.getAttribute("vo");
				String user_id = vo.getUser_id();
				//System.out.println(vo.toString());
				//System.out.println(user_id);
				
				//장바구니 리스트 가져와서 화면에 보내기
				ArrayList<CartVO> cvo = userService.getCartList(user_id);
				model.addAttribute("cvo", cvo);
				System.out.println(cvo.get(0).getCart_prod_price());
		return "user/cart";
	}

	@PostMapping("userCartForm")
	public String userCartForm() {
		
		return "redirect:/user/order_list";
	}
	
	//장바구니 리스트 삭제
	@PostMapping("/deleteCartForm")
	public int deleteCartForm(HttpSession session, @RequestParam("cart_no") int cart_no, CartVO cvo) {
		UserVO vo = (UserVO)session.getAttribute("vo");
		String user_id = vo.getUser_id();
		
		int result = 0;
		
		if(vo != null) {
			cvo.setUser_id(user_id);
			cvo.setCart_no(cart_no);
			userService.deleteCart(cvo);
			
			result = 1;
		}
		return result;
	}
	
	@GetMapping("/order_list")
	public String order_list() {
		
		return "user/order_list";
	}

}
