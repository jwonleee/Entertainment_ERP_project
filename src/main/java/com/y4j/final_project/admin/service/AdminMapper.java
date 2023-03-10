package com.y4j.final_project.admin.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.AuthorityVO;
import com.y4j.final_project.util.Criteria;

@Mapper
public interface AdminMapper {

	//관리자 등록 메서드
	public int registAdmin(AdminVO vo);

	//관리자 전체 정보 조회 메서드
	public ArrayList<AdminVO> getAdminList(Criteria cri);

	//관리자 전체 데이터 수 반환 메서드
	public int getAdminTotal(Criteria cri);

	//관리자 특정 1명 데이터 반환 메서드
	public AdminVO getAdminInfo(int admin_no);
	
	//관리자 특정 1명 데이터 반환 메서드
	public AdminVO getAdminInfo2(String admin_id);
	
	//관리자 권한 수정 메서드
	public int updateAdminAuthority(AdminVO vo);
	
	//권한 신청 목록 승인 처리 메서드
	public int approveAuth(AuthorityVO vo);
	
}
