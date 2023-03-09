package com.y4j.final_project.schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.EntertainerScheduleVO;
import com.y4j.final_project.command.EntertainerVO;
import com.y4j.final_project.command.ScheduleVO;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleMapper scheduleMapper;
	
	//일정등록
	@Override
	public int regist(ScheduleVO vo) {
		int result = scheduleMapper.regist(vo);
		return result; //성공시 1, 실패시 0
	}
	//schedule_no 가져옴
	@Override
	public int registMap(EntertainerScheduleVO vo2) {
		int result = scheduleMapper.registMap(vo2);
		return result;
	}
	
	//그룹 이름
	@Override
	public List<EntertainerVO> getEntType(EntertainerVO vo) {
		List<EntertainerVO> list = scheduleMapper.getEntType(vo);
		return list;
	}
	//배우 이름
	@Override
	public List<EntertainerVO> getEntType2(EntertainerVO vo) {
		List<EntertainerVO> list = scheduleMapper.getEntType2(vo); 
		return list;
	}
	
	//일정 리스트
	@Override
	public ArrayList<ScheduleVO> getList() {
		return scheduleMapper.getList();
	}
	
	
}
