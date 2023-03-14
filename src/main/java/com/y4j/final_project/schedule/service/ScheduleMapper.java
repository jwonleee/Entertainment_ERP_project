package com.y4j.final_project.schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.joda.time.LocalDate;

import com.y4j.final_project.command.EntertainerScheduleVO;
import com.y4j.final_project.command.EntertainerVO;
import com.y4j.final_project.command.ScheduleVO;
import com.y4j.final_project.util.Criteria;

@Mapper
public interface ScheduleMapper {

	//일정 등록
	public int regist(ScheduleVO vo);
	public int registMap(EntertainerScheduleVO vo2); //스케줄-엔터테이너 맵핑
	
	//그룹 리스트
	public List<EntertainerVO> getEntType(EntertainerVO vo); //가수 그룹
	public List<EntertainerVO> getEntType2(EntertainerVO vo); //배우 이름
	
	//일정 목록
	public ArrayList<ScheduleVO> getList(Criteria cri);
	public int getScheduleTotal(Criteria cri); //전체 게시글 수
	public int getTodayScheduleCnt(LocalDate now); //오늘 일정 수
	
	//일정 상세
	public ScheduleVO getDetail(int schedule_no);
	
	//일정 수정 화면(모달)
	public ScheduleVO getModifyForm(ScheduleVO vo);
	public int scheduleModify(ScheduleVO vo);
	
	//일정 삭제
	public int scheduleDelete(int schedule_no);
}
