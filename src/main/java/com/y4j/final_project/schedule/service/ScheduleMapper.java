package com.y4j.final_project.schedule.service;

import org.apache.ibatis.annotations.Mapper;

import com.y4j.final_project.command.ScheduleVO;

@Mapper
public interface ScheduleMapper {

	//일정 등록
	public int regist(ScheduleVO vo);
	
}
