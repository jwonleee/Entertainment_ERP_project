package com.y4j.final_project.user.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.y4j.final_project.command.UserVO;


@Mapper
public interface UserMapper {

	//등록 메서드
	public int registUser(UserVO vo);
	
	//유저 정보 조회 메서드
	public ArrayList<UserVO> getUserList();
}
