package com.y4j.final_project.schedule.service;

import java.util.ArrayList;
import java.util.List;

import com.y4j.final_project.command.EntertainerScheduleVO;
import com.y4j.final_project.command.EntertainerVO;
import com.y4j.final_project.command.ScheduleVO;

public interface ScheduleService {

	//일정 등록
	public int regist(ScheduleVO vo);
	public int registMap(EntertainerScheduleVO vo2);
	
	//그룹 리스트
	public List<EntertainerVO> getEntType(EntertainerVO vo);
	public List<EntertainerVO> getEntType2(EntertainerVO vo);

	//일정리스트
	public ArrayList<ScheduleVO> getList();
}
