package com.y4j.final_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.command.EntertainerScheduleVO;
import com.y4j.final_project.command.EntertainerVO;
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
		return "schedule/admin_scheduleReg";
	}
	
	@GetMapping("/admin_scheduleList")
	public String list(Model model) {
		
		ArrayList<ScheduleVO> list = scheduleService.getList();
		model.addAttribute("list",list);

		return "schedule/admin_scheduleList";
	}
	
	@GetMapping("/admin_scheduleDetail")
	public String retail() {
		return "schedule/admin_scheduleDetail";
	}
	
	
	//등록 요청
	@PostMapping("/registForm")
	public String registForm(ScheduleVO vo,
							 EntertainerScheduleVO vo2,
							 RedirectAttributes ra) {
		
		int result = scheduleService.regist(vo);
		int result2 = scheduleService.registMap(vo2);
		
//		String msg = result == 1 ? (result2 == 1 ? "정상 입력" : "등록실패"  ) : "등록실패";
//		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/schedule/admin_scheduleList";
	}
	
	//
	
	

	

}
