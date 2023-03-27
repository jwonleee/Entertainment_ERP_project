package com.y4j.final_project.user.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.y4j.final_project.command.CartListVO1;
import com.y4j.final_project.command.CartVO;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.util.Criteria;


@Mapper
public interface UserMapper {

	//회원가입 메서드
	public int registUser(UserVO vo);

	//아이디 중복 검사 및 로그인 시 아이디 있는지
	public int idCheck(String user_id);

	//유저 로그인
	public String login(String user_id);

	//유저 아이디 찾기
	public UserVO getUserId(UserVO vo);

	//유저 정보 수정 메서드
	public int updateUserInfo(UserVO vo);

	/////////////////////////////////////////////////////////////////////

	//유저 정보 조회 메서드
	public ArrayList<UserVO> getUserList(Criteria cri);

	//전체 유저 수 반환 메서드
	public int getUserTotal(Criteria cri);

	//유저 특정 1명 데이터 반환 메서드
	public UserVO getUserInfo(int user_no);

	//유저 특정 1명 데이터 반환 메서드
	public UserVO getUserInfo2(Object user_id);

	//////////////////////////////////////////////////////////////////////

	//유저의 장바구니 리스트 조회
	public ArrayList<CartListVO1> getCartList(String user_id);

	//장바구니 선택 상품 삭제
	public void deleteCart(CartVO cvo);
}
