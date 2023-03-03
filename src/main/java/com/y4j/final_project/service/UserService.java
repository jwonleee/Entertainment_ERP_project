package com.y4j.final_project.service;

import java.util.ArrayList;

import com.y4j.final_project.command.UsersVO;


public interface UserService {

	//유저 정보 조회 메서드
	public ArrayList<UsersVO> getList();
}
