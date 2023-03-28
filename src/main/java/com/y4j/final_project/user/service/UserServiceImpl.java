package com.y4j.final_project.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.AlbumCartVO;
import com.y4j.final_project.command.CartListVO1;
import com.y4j.final_project.command.CartVO;
import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.UserOrderVO;
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
	public ArrayList<UserVO> getUserList(Criteria cri) {
		return userMapper.getUserList(cri);
	}

	//전체 데이터 수 반환 메서드
	public int getUserTotal(Criteria cri) {
		return userMapper.getUserTotal(cri);
	}

	//비밀번호 암호화
	@Override
	public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}

	//아이디 중복 검사 및 로그인 시 아이디 있는지 검사
	@Override
	public int idCheck(String user_id) {
		int cnt = userMapper.idCheck(user_id);

		return cnt;
	}

	//유저 로그인
	@Override
	public String login(String user_id) {
		return userMapper.login(user_id);
	}

	//유저 아이디 찾기 
	@Override
	public UserVO getUserId(UserVO vo) {
		return userMapper.getUserId(vo);
	}

	//유저 정보 수정 메서드
	public int updateUserInfo(UserVO vo) {
		return userMapper.updateUserInfo(vo);
	}

	//유저 특정 1명 데이터 반환 메서드
	public UserVO getUserInfo(int user_no) {
		return userMapper.getUserInfo(user_no);
	}

	//유저 특정 1명 데이터 반환 메서드
	public UserVO getUserInfo2(Object user_id) {
		return userMapper.getUserInfo2(user_id);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	//유저의 장바구니 상품 추가
//	@Override
//	public int addCart(CartVO cvo) {
//		return userMapper.addCart(cvo);
//	}

	//유저의 장바구니 리스트 조회 - product
	@Override
	public List<CartVO> getCartList(String user_id) {
		return userMapper.getCartList(user_id);
	}
	
	//유저의 장바구니 상품 삭제 - 개별, 선택
	@Override
	public int deleteCartOne(CartVO cvo) {
		return userMapper.deleteCartOne(cvo);
	}



//	//유저의 장바구니 상품 주문
//	@Override
//	public int orderCart(OrderHistoryVO ovo) {
//		return userMapper.orderCart(ovo);
//	}


}
