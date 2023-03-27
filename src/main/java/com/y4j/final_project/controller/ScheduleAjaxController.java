package com.y4j.final_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.command.EntertainerVO;
import com.y4j.final_project.command.ScheduleVO;
import com.y4j.final_project.schedule.service.ScheduleService;

@RestController
public class ScheduleAjaxController {
	
	@Autowired
	public ScheduleService scheduleService;
	
	//그룹 이름
	@PostMapping("/getEntType")
	public List<EntertainerVO> getEntType(@RequestBody EntertainerVO vo){
		return scheduleService.getEntType(vo);
	}
	
	//배우 이름
	@PostMapping("/getEntType2")
	public List<EntertainerVO> getEntType2(@RequestBody EntertainerVO vo){
		return scheduleService.getEntType2(vo);
	}
	
	//일정 수정 화면(모달)
	@PostMapping("/getModifyForm")
	public ScheduleVO getModifyForm(@RequestBody ScheduleVO vo){
		return scheduleService.getModifyForm(vo);
	}
	
	//아티스트-스케줄 유무 확인
	@GetMapping("/getSchedule/{artistSelected}")
	public ArrayList<ScheduleVO> getSchedule(@PathVariable("artistSelected") String artistSelec) {
		System.out.println(artistSelec);
		return scheduleService.getSchedule(artistSelec);
	}
	

}
