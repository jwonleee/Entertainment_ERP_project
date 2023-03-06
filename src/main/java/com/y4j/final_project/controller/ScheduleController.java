package com.y4j.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.y4j.final_project.command.ScheduleVO;
import com.y4j.final_project.schedule.service.ScheduleService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	@Qualifier("scheduleService")
	private ScheduleService scheduleService;
	
	@GetMapping("/admin_scheduleReg")
	public String reg() {
		return "/schedule/admin_scheduleReg";
	}
	
	@GetMapping("/admin_scheduleList")
	public String list() {
		return "/schedule/admin_scheduleList";
	}
	
	@GetMapping("/admin_scheduleDetail")
	public String retail() {
		return "/schedule/admin_scheduleDetail";
	}
	
	
	
	//등록 요청
	@PostMapping("/registForm")
	public String registForm(ScheduleVO vo) {
		
		int result = scheduleService.regist(vo);
		System.out.println(result);
		
		return "redirect:/schedule/admin_scheduleList";
	}
	
}
