package com.y4j.final_project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.AlbumCartVO;
import com.y4j.final_project.command.CartListVO1;
import com.y4j.final_project.command.CartVO;
import com.y4j.final_project.command.MessageVO;
import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.message.service.MessageService;
import com.y4j.final_project.user.service.UserService;
import com.y4j.final_project.util.GoogleAPI;
import com.y4j.final_project.util.KakaoAPI;
import com.y4j.final_project.util.NaverAPI;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MessageService messageService;

	//	@Autowired
	//	private EmailService emailService;

	@Autowired
	private KakaoAPI kakao;

	@Autowired
	private NaverAPI naver;

	@Autowired
	private GoogleAPI google;

	// 회원가입
	@GetMapping("/user_join")
	public String user_join() {

		return "user/user_join";
	}

	@PostMapping("/userJoinForm")
	public String userJoinForm(@Valid UserVO vo, Errors errors, Model model, RedirectAttributes ra) {

		if (errors.hasErrors()) { // 에러가 있다면 true, 없다면 false
			List<FieldError> list = errors.getFieldErrors();
			for (FieldError err : list) {
				if (err.isBindingFailure()) { // 유효성 검사의 실패가 아니라, 자바 내부의 에러라면 true 반환
					model.addAttribute("valid_" + err.getField(), "형식이 올바르지 않습니다");
				} else { // 유효성 검사에 실패한 목록
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			model.addAttribute("vo", null);
			/* ra.addFlashAttribute("message", "회원가입에 실패하였습니다"); */
			return "user/user_join"; // 실패하면 원래 화면으로
		}
		// 비밀번호 암호화
		String pw = passwordEncoder.encode(vo.getUser_pw());
		vo.setUser_pw(pw);
		userService.registUser(vo);
		model.addAttribute("vo", vo); // 사용자가 작성한 값을 화면으로 보내기(vo에 사용자가 작성한 값 들어있음)
		/* ra.addFlashAttribute("message", "반갑습니다 ${user_id}님!"); */
		
		return "user/user_login";
	}

	// //이메일 인증 - 회원가입&비밀번호 찾기
	// @PostMapping("/emailConfirm")
	// public String emailConfirm(@RequestParam String email, HttpServletRequest
	// request) throws Exception {
	//
	// String confirm = emailService.sendSimpleMessage(email);
	//
	// return confirm;
	// }

	////////////////////////////////////////////////////////////////////////////////////////////////

	// 소셜 간편 로그인 - 카카오
	@GetMapping("/kakao")
	public String kakao(@RequestParam("code") String code) {

		String token = kakao.getAccessToken(code);

		System.out.println("어세스토큰: " + token);

		Map<String, Object> map = kakao.getUserInfo(token);
		System.out.println("사용자 데이터: " + map.toString());

		return "redirect:/";
	}

	// 소셜 간편 로그인 - 네이버
	@GetMapping("/naver")
	public String naver(@RequestParam("code") String code, @RequestParam("state") String state) {

		String token = naver.getAccessToken(code, state);

		System.out.println("어세스토큰: " + token);

		Map<String, Object> map = naver.getUserInfo(token);
		System.out.println("사용자 데이터: " + map.toString());

		return "redirect:/";
	}

	// 소셜 간편 로그인 - 구글
	@GetMapping("/google")
	public String google(@RequestParam("code") String code, @RequestParam("state") String state) {
		String token = google.getAccessToken(code, state);

		System.out.println("어세스토큰: " + token);

		Map<String, Object> map = google.getUserInfo(token);
		System.out.println("사용자 데이터: " + map.toString());

		return "redirect:/";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 회원 로그인
	@GetMapping("/user_login")
	public String user_login() {

		return "user/user_login";
	}

	@PostMapping("/userLoginForm")
	public String userLoginForm(@RequestParam("user_id") String user_id, @RequestParam("user_pw") String user_pw,
			UserVO vo, HttpServletRequest request, Model model, RedirectAttributes ra) {

		int count = userService.idCheck(user_id); // db에 저장되어있는 아이디
		String saved_pw = userService.login(user_id); // db에 저장되어있는 비밀번호

		// 세션이 있으면 세션 반환, 없으면 새로 생성해서 반환
		// getSession()은 기본값이 true인데 false로 설정할 경우, 세션이 없을 때 새로 생성하지 않고 null 반환
		HttpSession session = request.getSession();

		if (count == 1 && passwordEncoder.matches(user_pw, saved_pw)) {

			// 세션에 회원정보 저장 - 아이디로 가져와서
			session.removeAttribute("admin_id");
			session.removeAttribute("admin_type");
			session.removeAttribute("admin_name");
			session.setAttribute("user_id", user_id);
			session.setAttribute("vo", userService.getUserInfo2(user_id));
			 
			return "user/mypage";
		} else {
			session.setAttribute("vo", null);
			//model.addAttribute("vo",vo);
			
			return "user/user_login";
		}
	}
	
	@GetMapping("/userLogoutForm")
	public String userLogoutForm(HttpSession session, RedirectAttributes ra) {
		
		session.invalidate();
		ra.addFlashAttribute("msg", "정상적으로 로그아웃 되었습니다.");
		
		return "redirect:/";
	}

	// 아이디 찾기
	@GetMapping("user_find_id")
	public String user_find_id() {

		return "user/user_find_id";
	}

	@PostMapping("/userFindIdForm")
	public String userFindIdForm(UserVO vo, Model model) {
		UserVO result = userService.getUserId(vo); // db에 담겨있는 vo값들
		// System.out.println(result);

		model.addAttribute("vo", result); // model에 담아서 화면에 뿌리기
		return "user/user_find_id_result"; // 결과페이지로
	}

	// 아이디찾기 결과
	@GetMapping("user_find_id_result")
	public String user_find_id_result() {
		return "user/user_find_id_result";
	}

	// 비밀번호 찾기
	@GetMapping("user_find_pw")
	public String user_find_pw() {

		return "user/user_find_pw";
	}

	// 마이페이지
	@GetMapping("/mypage")
	public String mypage() {

		return "user/mypage";
	}

	// 회원정보수정
	@GetMapping("/user_info_edit")
	public String user_info_edit(HttpSession session) {

		UserVO vo = (UserVO) session.getAttribute("vo"); // 세션에 저장된 회원 정보
		String user_id = (String) session.getAttribute("user_id"); // 세션에 저장된 회원 아이디

		return "user/user_info_edit";
	}

	@PostMapping("/userInfoForm")
	public String userInfoForm(@Valid UserVO vo, Errors errors, HttpServletRequest request, Model model,
			@RequestParam("user_id") String user_id) {

		HttpSession session = request.getSession();
		session.setAttribute("vo", vo);
		int result = userService.updateUserInfo(vo);
		// System.out.println(result);
		// System.out.println(vo);

		if (errors.hasErrors()) { // 에러가 있다면 true, 없다면 false
			List<FieldError> list = errors.getFieldErrors();
			for (FieldError err : list) {
				if (err.isBindingFailure()) { // 유효성 검사의 실패가 아니라, 자바 내부의 에러라면 true 반환
					model.addAttribute("valid_" + err.getField(), "형식이 올바르지 않습니다");
				} else { // 유효성 검사에 실패한 목록
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			model.addAttribute("vo", vo); // 사용자가 작성한 값을 화면으로 보내기(vo에 사용자가 작성한 값 들어있음)

			return "user/user_info_edit"; // 실패하면 원래 화면으로
		}
		String pw = passwordEncoder.encode(vo.getUser_pw());
		model.addAttribute("vo", vo);
		// 비밀번호 암호화
		vo.setUser_pw(pw);

		userService.updateUserInfo(vo);

		return "user/mypage";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	//장바구니 담기 - 화면 출력
//	@GetMapping("/product_cart")
//	public String product_cart (CartVO vo, Model model) {
//		
//		ArrayList<CartVO> cart = productService.prod_cartList(vo);
//		model.addAttribute("cart", cart);
//		return "product/product_cart";
//	}
	
	// 장바구니 리스트
	@GetMapping("/cart")
	public String cart(HttpSession session, Model model, UserVO vo, CartVO cvo, RedirectAttributes ra) {

		// 세션에 저장되어 있는 유저 아이디 가져오기
		   String user_id=(String)session.getId();
		   vo.setUser_id(user_id);

		if (user_id == null) {
			return "redirect:/user/user_login";
		}
		// 장바구니 리스트 가져와서 화면에 보내기
		List<CartVO> cvo1 = userService.getCartList(user_id);
		model.addAttribute("cvo1", cvo1);
		return "user/cart";
	}

	// 카트리스트에서 주문하기
//	@PostMapping("/cartList")
//	public String order(@RequestParam(value = "check[]") List<String> chArr, HttpSession session, OrderHistoryVO order,
//			UserVO vo, CartVO cvo) {
//		
//		vo = (UserVO) session.getAttribute("vo");
//		String user_id = vo.getUser_id();
//		userService.orderCart(order);// 주문 테이블 insert
//
//		int cart_no = 0;
//		int result = 0;
//
//		for (String i : chArr) {
//			cart_no = Integer.parseInt(i);
//			cvo.setCart_no(cart_no);
//			result = userService.deleteCartOne(cvo);
//		}
//		return "user/mypage";
//	}

	// 빈 장바구니
	@GetMapping("/empty_cart")
	public String empty_cart() {
		return "user/empty_cart";
	}
	
	@GetMapping("/user_msg")
	public String user_msg(HttpSession session, Model model) {

		if(session.getAttribute("user_id") != null) {
			model.addAttribute("uncheckedMsgNum", messageService.getUncheckedMsg(session.getAttribute("user_id")));
		
		} else if(session.getAttribute("admin_id") != null) {
			model.addAttribute("uncheckedMsgNum", messageService.getUncheckedMsg(session.getAttribute("admin_id")));
		}
			
		return "user/user_msg";
	}
	
	@PostMapping("/sendMsgForm")
	public String sendMsgForm(MessageVO vo, RedirectAttributes ra) {
		
		MessageVO msgVO = null;
		
		if(vo.getMsg_writer_type().equals("user") && vo.getMsg_receiver_type().equals("user")) {
			UserVO writerVO = userService.getUserInfo2(vo.getMsg_writer_id());
			UserVO receiverVO = userService.getUserInfo2(vo.getMsg_receiver_id());
			
			try {
				msgVO = MessageVO.builder()
						  .msg_writer_no(writerVO.getUser_no())
						  .msg_writer_id(writerVO.getUser_id())
						  .msg_writer_type(vo.getMsg_writer_type())
						  .msg_writer_name(writerVO.getUser_name())
						  .msg_receiver_no(receiverVO.getUser_no())
						  .msg_receiver_id(receiverVO.getUser_id())
						  .msg_receiver_type(vo.getMsg_receiver_type())
						  .msg_receiver_name(receiverVO.getUser_name())
						  .msg_title(vo.getMsg_title())
						  .msg_content(vo.getMsg_content())
						  .build();
				
			} catch (Exception e) {
				ra.addFlashAttribute("msg", "쪽지 발송에 실패했습니다.");
				return "redirect:/user/user_msg";
			}
			
		} else if(vo.getMsg_writer_type().equals("user") && vo.getMsg_receiver_type().equals("admin")) {
			UserVO writerVO = userService.getUserInfo2(vo.getMsg_writer_id());
			AdminVO receiverVO = adminService.getAdminInfo2(vo.getMsg_receiver_id());
			
			try {
				msgVO = MessageVO.builder()
						  .msg_writer_no(writerVO.getUser_no())
						  .msg_writer_id(writerVO.getUser_id())
						  .msg_writer_type(vo.getMsg_writer_type())
						  .msg_writer_name(writerVO.getUser_name())
						  .msg_receiver_no(receiverVO.getAdmin_no())
						  .msg_receiver_id(receiverVO.getAdmin_id())
						  .msg_receiver_type(vo.getMsg_receiver_type())
						  .msg_receiver_name(receiverVO.getAdmin_name())
						  .msg_title(vo.getMsg_title())
						  .msg_content(vo.getMsg_content())
						  .build();
				
			} catch (Exception e) {
				ra.addFlashAttribute("msg", "쪽지 발송에 실패했습니다.");
				return "redirect:/user/user_msg";
			}
			
		} else if(vo.getMsg_writer_type().equals("admin") && vo.getMsg_receiver_type().equals("user")) {
			AdminVO writerVO = adminService.getAdminInfo2(vo.getMsg_writer_id());
			UserVO receiverVO = userService.getUserInfo2(vo.getMsg_receiver_id());
			
			try {
				msgVO = MessageVO.builder()
						  .msg_writer_no(writerVO.getAdmin_no())
						  .msg_writer_id(writerVO.getAdmin_id())
						  .msg_writer_type(vo.getMsg_writer_type())
						  .msg_writer_name(writerVO.getAdmin_name())
						  .msg_receiver_no(receiverVO.getUser_no())
						  .msg_receiver_id(receiverVO.getUser_id())
						  .msg_receiver_type(vo.getMsg_receiver_type())
						  .msg_receiver_name(receiverVO.getUser_name())
						  .msg_title(vo.getMsg_title())
						  .msg_content(vo.getMsg_content())
						  .build();
				
			} catch (Exception e) {
				ra.addFlashAttribute("msg", "쪽지 발송에 실패했습니다.");
				return "redirect:/user/user_msg";
			}
			
		} else if(vo.getMsg_writer_type().equals("admin") && vo.getMsg_receiver_type().equals("admin")) {
			AdminVO writerVO = adminService.getAdminInfo2(vo.getMsg_writer_id());
			AdminVO receiverVO = adminService.getAdminInfo2(vo.getMsg_receiver_id());
			
			try {
				msgVO = MessageVO.builder()
						  .msg_writer_no(writerVO.getAdmin_no())
						  .msg_writer_id(writerVO.getAdmin_id())
						  .msg_writer_type(vo.getMsg_writer_type())
						  .msg_writer_name(writerVO.getAdmin_name())
						  .msg_receiver_no(receiverVO.getAdmin_no())
						  .msg_receiver_id(receiverVO.getAdmin_id())
						  .msg_receiver_type(vo.getMsg_receiver_type())
						  .msg_receiver_name(receiverVO.getAdmin_name())
						  .msg_title(vo.getMsg_title())
						  .msg_content(vo.getMsg_content())
						  .build();
				
			} catch (Exception e) {
				ra.addFlashAttribute("msg", "쪽지 발송에 실패했습니다.");
				return "redirect:/user/user_msg";
			}
			
		}
		
		int result = messageService.sendMsg(msgVO);
		String msg = (result == 1) ? "정상적으로 발송 처리되었습니다." : "쪽지 발송에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/user/user_msg";
	}

}
