package com.y4j.final_project.authority.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.AuthorityVO;
import com.y4j.final_project.util.Criteria;

@Service @Primary
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityMapper authorityMapper;

	//관리자 회원가입 완료 시, 권한 신청 정보 등록
	public int applyAuthority(AuthorityVO vo) {
		
		return authorityMapper.applyAuthority(vo);
	}

	//관리자 권한 신청 목록 조회 메서드
	public ArrayList<AuthorityVO> getAuthorityApplyList(Criteria cri) {
		return authorityMapper.getAuthorityApplyList(cri);
	}

	//관리자 권한 신청 총 데이터 수 반환 메서드
	public int getAuthorityApplyTotal(Criteria cri) {
		return authorityMapper.getAuthorityApplyTotal(cri);
	}
	
	//관리자 권한 신청 승인 메서드
	public int approveAuth(AuthorityVO vo) {
		return authorityMapper.approveAuth(vo);
	}
	
	//관리자 권한 신청 반려 메서드
	public int rejectAuth(AuthorityVO vo) {
		return authorityMapper.rejectAuth(vo);
	}

}
