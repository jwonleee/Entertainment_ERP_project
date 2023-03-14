package com.y4j.final_project.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//유저 등록 메서드
		public int registUser(UserVO vo) {

			return userMapper.registUser(vo);
		}
		
		//유저 정보 조회 메서드
		@Override
		public ArrayList<UserVO> getUserList(Criteria cri) {
			return userMapper.getUserList(cri);
		}
		
		//유저 정보 수정 메서드
		@Override
		public int updateUser(UserVO vo) {

			return userMapper.updateUser(vo);
		}

		//전체 데이터 수 반환 메서드
		@Override
		public int getUserTotal(Criteria cri) {

			return userMapper.getUserTotal(cri);
		}

		//비밀번호 암호화
	  @Override
	     public PasswordEncoder passwordEncoder() {
	          return this.passwordEncoder;
	     }

	  //아이디 중복 검사
	@Override
	public int idCheck(String user_id) {
		int cnt = userMapper.idCheck(user_id);
		
		return cnt;
	}

	//유저 로그인
//	@Override
//	public int getUserAccount(UserVO vo) {
//
//		return userMapper.getUserAccount(vo);
//	}
	
	


	
	
	
	
}
