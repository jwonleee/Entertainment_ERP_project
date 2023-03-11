package com.y4j.final_project.audition.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.AuditionVO;
import com.y4j.final_project.util.Criteria;

@Service @Primary
public class AuditionServiceImpl implements AuditionService{

	@Autowired
	private AuditionMapper auditionMapper;
	
	
	//오디션 지원폼 등록 메서드
	public int registAud(AuditionVO vo) {
		
//		AuditionFileVO afVO = AuditionFileVO.builder()
//							  .audition_cv_file_path(null)
//							  .build();
//		auditionMapper.registAudFile(afVO);
		
		return auditionMapper.registAud(vo);
	}

	//오디션 지원폼 전체 조회 메서드	
	public ArrayList<AuditionVO> getAudList(Criteria cri) {
		return auditionMapper.getAudList(cri);
	}
	
	//전체 오디션 지원 수 반환 메서드
	public int getAudTotal(Criteria cri) {
		return auditionMapper.getAudTotal(cri);
	}
	
}
