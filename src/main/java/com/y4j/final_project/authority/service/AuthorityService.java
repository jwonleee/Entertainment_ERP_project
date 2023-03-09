package com.y4j.final_project.authority.service;

import java.util.ArrayList;

import com.y4j.final_project.command.AuthorityVO;
import com.y4j.final_project.util.Criteria;

public interface AuthorityService {

	//관리자 회원가입 완료 시, 권한 신청 정보 등록
		public int applyAuthority(AuthorityVO vo);
		
		//관리자 권한 신청 목록 조회 메서드
		public ArrayList<AuthorityVO> getAuthorityApplyList(Criteria cri);
		
		//관리자 권한 신청 총 데이터 수 반환 메서드
		public int getAuthorityApplyTotal(Criteria cri);
		
		//권한 신청 목록 승인 처리 메서드
		public int approveAuth(AuthorityVO vo);
		
		//권한 신청 목록 반려 처리 메서드
		public int rejectAuth(AuthorityVO vo);
		
}
