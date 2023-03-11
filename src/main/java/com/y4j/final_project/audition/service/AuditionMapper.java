package com.y4j.final_project.audition.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.y4j.final_project.command.AuditionFileVO;
import com.y4j.final_project.command.AuditionVO;
import com.y4j.final_project.util.Criteria;


@Mapper
public interface AuditionMapper {

	//오디션 지원서 저장 메서드
	public int registAud(AuditionVO vo);

	//오디션 지원서 내 파일 정보 저장	
	public int registAudFile(AuditionFileVO vo);
	
	//오디션 지원서 전체 조회 메서드	
	public ArrayList<AuditionVO> getAudList(Criteria cri);
	
	//전체 오디션 지원서 수 반환 메서드
	public int getAudTotal(Criteria cri);
	
}
