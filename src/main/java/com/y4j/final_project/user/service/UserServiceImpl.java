package com.y4j.final_project.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.util.Criteria;


@Service @Primary
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	//유저 등록 메서드
	public int registUser(UserVO vo) {
		return userMapper.registUser(vo);
	}
	
	//유저 정보 조회 메서드
	public ArrayList<UserVO> getUserList(Criteria cri) {
		return userMapper.getUserList(cri);
	}

	//전체 유저 수 반환
	public int getUserTotal(Criteria cri) {

		return userMapper.getUserTotal(cri);
	}

	//유저 특정 1명 데이터 반환 메서드
	public UserVO getUserInfo(int user_no) {
		return userMapper.getUserInfo(user_no);
	}
	
	//유저 특정 1명 데이터 반환 메서드
	public UserVO getUserInfo2(Object user_id) {
		return userMapper.getUserInfo2(user_id);
	}
	
	//유저 정보 수정 메서드
	public int updateUserInfo(UserVO vo) {
		return userMapper.updateUserInfo(vo);
	}
	
}
