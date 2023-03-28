package com.y4j.final_project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.command.CartVO;
import com.y4j.final_project.command.CartListVO1;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.user.service.UserService;

@RestController
public class UserAjaxController {

	@Autowired
	UserService userService;

	// 아이디 중복체크
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("user_id") String user_id) {

		int cnt = userService.idCheck(user_id);
		return cnt;
	}

	//유저의 장바구니 상품별 삭제
	@PostMapping("/deleteCartOne")
	@ResponseBody
	public int deleteCartOne(@RequestParam(value = "cart_no", required = false) int cart_no, CartVO cvo) {
		if(cvo == null) {
			return 0;
		}
		int result = userService.deleteCartOne(cvo);
		System.out.println(result);
		return result;
	}

	// 유저의 장바구니 선택 상품 삭제
	@PostMapping("/checkedDelCart")
	@ResponseBody
	public int checkedDelCart(@RequestParam(value = "check[]") List<String> chArr, CartVO cvo) {
		int result = 0;
		int cart_no = 0;

		if (cvo == null) {
			return 0;
		} 
			for (String i : chArr) {
				cart_no = Integer.parseInt(i);
				cvo.setCart_no(cart_no);
				result = userService.deleteCartOne(cvo);
			}
			return result;
	}

	
	

}
