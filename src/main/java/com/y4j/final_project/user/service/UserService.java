package com.y4j.final_project.user.service;

import java.util.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.y4j.final_project.command.CartVO;
import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.UserOrderVO;
import com.y4j.final_project.command.AlbumCartVO;
import com.y4j.final_project.command.CartListVO1;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.util.Criteria;

public interface UserService {

	// 비밀번호 암호화
	public PasswordEncoder passwordEncoder();

	// 유저 등록 메서드
	public int registUser(UserVO vo);

	// 아이디 중복 검사 및 로그인 시 아이디 있는지
	public int idCheck(String user_id);

	// 유저 정보 조회 메서드
	public ArrayList<UserVO> getUserList(Criteria cri);

	// 유저 로그인
	public String login(String user_id);

	// 유저 아이디 찾기
	public UserVO getUserId(UserVO vo);

	// 유저 정보 수정 메서드
	public int updateUserInfo(UserVO vo);

	/////////////////////////////////////////////////////

	// 전체 유저 수 반환 메서드
	public int getUserTotal(Criteria cri);

	// 유저 특정 1명 데이터 반환 메서드
	public UserVO getUserInfo(int user_no);

	// 유저 특정 1명 데이터 반환 메서드
	public UserVO getUserInfo2(Object user_id);

	///////////////////////////////////////////////////////

	// 유저의 장바구니 상품 추가
	// 상품 상세 페이지 -> 장바구니 담기
	public int addCart(CartVO vo);

	// 유저의 장바구니 product 리스트 조회
	public List<CartVO> getCartList(String user_id);

	// 유저의 장바구니 상품 삭제 - 개별, 선택
	public int deleteCartOne(CartVO cvo);

//	// 유저의 장바구니 주문
//	public int orderCart(OrderHistoryVO ovo);

}
