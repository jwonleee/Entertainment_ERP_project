package com.y4j.final_project.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import com.y4j.final_project.command.EntertainerScheduleVO;
import com.y4j.final_project.command.EntertainerVO;
import com.y4j.final_project.command.ScheduleVO;
import com.y4j.final_project.util.Criteria;

public interface ScheduleService {

	//일정 등록
	public int regist(ScheduleVO vo);
	public int registMap(EntertainerScheduleVO vo2); //스케줄-엔터테이너 맵핑
	public ArrayList<ScheduleVO> getSchedule(String artistSelect); //아티스트-스케줄 유무 확인
	
	//그룹 리스트
	public List<EntertainerVO> getEntType(EntertainerVO vo); //가수 그룹
	public List<EntertainerVO> getEntType2(EntertainerVO vo); //배우 이름

	//일정 목록
	public ArrayList<ScheduleVO> getList(Criteria cri);
	public int getScheduleTotal(Criteria cri); //전체 게시글 수
	public int getTodayScheduleCnt(LocalDate now); //오늘 일정 수
	
	//일반페이지 일정 - axios
	public List<Map<String, Object>> axiosGetList();
	
	//일정 상세
	public ScheduleVO getDetail(int schedule_no);
		
	//일정 수정 화면(모달)
	public ScheduleVO getModifyForm(ScheduleVO vo);
	public int scheduleModify(ScheduleVO vo);
	
	//일정 삭제
	public int scheduleDelete(int schedule_no);
	
}
