package com.y4j.final_project.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.schedule.service.ScheduleService;

@RestController
public class ReactController {

	@Autowired
	@Qualifier("scheduleService")
	private ScheduleService scheduleService;
	
	@GetMapping("/axiosgetList")
	public List<Map<String, Object>> axiosGetList(@Param("schedule_start_time") String schedule_start_time,
												  @Param("ent_name") String ent_name){
		
		System.out.println(schedule_start_time);
		System.out.println(ent_name);
		
		List<Map<String, Object>> map = scheduleService.axiosGetList(schedule_start_time, ent_name);
		
        System.out.println(map.toString());
        System.out.println(1);
        
		return map;
    }
}
