package com.y4j.final_project.user.service;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.validation.Errors;

import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.util.Criteria;


@Mapper
public interface UserMapper {

	//등록 메서드
	public int registUser(UserVO vo);
	
	//유저 정보 조회 메서드
	public ArrayList<UserVO> getUserList(Criteria cri);
	
	//전체 데이터 수 반환 메서드
	public int getUserTotal(Criteria cri);
}
