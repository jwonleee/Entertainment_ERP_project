package com.y4j.final_project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.schedule.service.ScheduleService;

@RestController
public class AjaxReactController {

	@Autowired
	@Qualifier("scheduleService")
	private ScheduleService scheduleService;
	
	@GetMapping("/axiosgetList")
	public List<Map<String, Object>> axiosGetList(){
		List<Map<String, Object>> map = scheduleService.axiosGetList();
        return map;
    }
}
