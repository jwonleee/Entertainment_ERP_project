package com.y4j.final_project.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.y4j.final_project.audition.service.AuditionService;
import com.y4j.final_project.command.AuditionVO;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.PageVO;

@Controller
@RequestMapping("/audition")
public class AuditionController {

	@Autowired
	AuditionService auditionService;
	
	
	@GetMapping("/audition_notice")
	public String audition_notice() {
		
		return "audition/audition_notice";
	}
	
	@GetMapping("/admin_audition_list")
	public String admin_audition_list(Model model,
			HttpSession session, Criteria cri) {
		
		ArrayList<AuditionVO> list = auditionService.getAudList(cri);
		model.addAttribute("list", list);
		
		int total = auditionService.getAudTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("pageVO", pageVO);
		
		
		return "audition/admin_audition_list";
	}
	
	@GetMapping("/admin_audition_register")
	public String admin_audition_register() {
		
		return "audition/admin_audition_register";
	}
	
	

}
