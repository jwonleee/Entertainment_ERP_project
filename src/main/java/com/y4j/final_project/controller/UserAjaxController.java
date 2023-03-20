package com.y4j.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.user.service.UserService;

@RestController
public class UserAjaxController {
	
	@Autowired
	UserService userService;
	
	//아이디 중복체크
		@PostMapping("/idCheck")
		@ResponseBody
		public int idCheck(@RequestParam("user_id") String user_id) {

			int cnt = userService.idCheck(user_id);
			return cnt;
		}

}
