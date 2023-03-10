package com.y4j.final_project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.y4j.final_project.command.EntertainerVO;
import com.y4j.final_project.schedule.service.ScheduleMapper;

@SpringBootTest
public class ScheduleTest {

	@Autowired
	ScheduleMapper scheduleMapper;
	
//	public void getGroupList() {
//		System.out.println(1 + scheduleMapper.getGroupList().toString());
//	}
	
}
