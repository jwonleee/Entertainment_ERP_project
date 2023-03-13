package com.y4j.final_project.audition.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.y4j.final_project.command.AuditionFileVO;
import com.y4j.final_project.command.AuditionVO;
import com.y4j.final_project.util.Criteria;

public interface AuditionService {

	//오디션 지원폼 등록 메서드
	public int registAud(@Param("vo") AuditionVO vo, @Param("list") List<MultipartFile> list);

	//오디션 지원폼 전체 조회 메서드	
	public ArrayList<AuditionVO> getAudList(Criteria cri);
	
	//전체 오디션 지원 수 반환 메서드
	public int getAudTotal(Criteria cri);
	
	//오디션 지원서 특정 1개 데이터 반환 메서드
	public AuditionVO getAudCv(int audition_cv_no);
	
	//이미지 데이터 조회
	public List<AuditionFileVO> getAudFile(AuditionVO vo);
	
	//오디션 1차 합격 처리
	public int passFirstStage(int audition_cv_no);

	//오디션 1차 불합격 처리
	public int failFirstStage(int audition_cv_no);
	
}
