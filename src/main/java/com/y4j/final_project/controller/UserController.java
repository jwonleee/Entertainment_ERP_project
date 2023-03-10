package com.y4j.final_project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.user.service.UserService;
import com.y4j.final_project.util.Criteria;

import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user_login")
	public String user_login() {

		return "user/user_login";
	}

	@GetMapping("/user_join")
	public String user_join() {

		return "user/user_join";
	}

	@PostMapping("/userJoinForm")
	public String userJoinForm(@Valid UserVO vo, Errors errors, Model model, Criteria cri) {

		System.out.println(errors.getFieldErrorCount());

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
		vo.setUser_no(userService.getUserTotal(cri) + 1);
		model.addAttribute("vo", vo); 
		userService.registUser(vo);
		return "redirect:/user/user_login";
	}


}
