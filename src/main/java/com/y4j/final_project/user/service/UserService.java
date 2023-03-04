package com.y4j.final_project.user.service;

import java.util.ArrayList;

import com.y4j.final_project.command.UserVO;


public interface UserService {

	//유저 등록 메서드
	public int registUser(UserVO vo);
	
	//유저 정보 조회 메서드
	public ArrayList<UserVO> getUserList();
}
