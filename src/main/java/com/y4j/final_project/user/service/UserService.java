package com.y4j.final_project.user.service;

import java.util.*;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.util.Criteria;


public interface UserService {

	//유저 등록 메서드
	public int registUser(UserVO vo);
	
	//유저 정보 조회 메서드
	public ArrayList<UserVO> getUserList(Criteria cri);

	//전체 데이터 수 반환 메서드
	public int getUserTotal(Criteria cri);
	
	
}
