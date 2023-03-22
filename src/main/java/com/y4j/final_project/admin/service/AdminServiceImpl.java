package com.y4j.final_project.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.AdminVO;
import com.y4j.final_project.command.AuthorityVO;
import com.y4j.final_project.util.Criteria;

@Service @Primary
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;


	//관리자 등록 메서드
	public int registAdmin(AdminVO vo) {
		return adminMapper.registAdmin(vo);
	}

	//관리자 정보 조회 메서드
	public ArrayList<AdminVO> getAdminList(Criteria cri) {
		return adminMapper.getAdminList(cri);
	}

	//관리자 전체 데이터 수 반환 메서드
	public int getAdminTotal(Criteria cri) {
		return adminMapper.getAdminTotal(cri);
	}

	//관리자 특정 1명 데이터 반환 메서드
	public AdminVO getAdminInfo(int admin_no) {
		return adminMapper.getAdminInfo(admin_no);
	}

	//관리자 특정 1명 데이터 반환 메서드
	public AdminVO getAdminInfo2(String admin_id) {
		return adminMapper.getAdminInfo2(admin_id);
	}

	//관리자 권한 수정 메서드
	public int updateAdminAuthority(AdminVO vo) {
		return adminMapper.updateAdminAuthority(vo);
	}

	//권한 신청 목록 승인 처리 메서드
	public int approveAuth(AuthorityVO vo) {
		return adminMapper.approveAuth(vo);
	}

	//관리자 아이디 중복 검사 및 로그인 시 아이디 존재 유무 확인
	@Override
	public int idCheck2(String admin_id) {
		return adminMapper.idCheck2(admin_id);
	}
	
	//관리자 로그인
	public String login(String admin_id) {
		return adminMapper.login(admin_id);
	};

}
