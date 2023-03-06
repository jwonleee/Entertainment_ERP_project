package com.y4j.final_project.user.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.UserVO;


@Service @Primary
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	//유저 등록 메서드
	public int registUser(UserVO vo) {
		return userMapper.registUser(vo);
	}
	
	//유저 정보 조회 메서드
	public ArrayList<UserVO> getUserList() {
		return userMapper.getUserList();
	}
	
	
	
}
