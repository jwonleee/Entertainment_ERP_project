package com.y4j.final_project.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
