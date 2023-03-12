package com.y4j.final_project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.audition.service.AuditionService;
import com.y4j.final_project.command.AuditionVO;
import com.y4j.final_project.user.service.UserService;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.PageVO;

@Controller
@RequestMapping("/audition")
public class AuditionController {

	@Autowired
	AuditionService auditionService;
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/audition_notice")
	public String audition_notice(HttpSession session, Model model) {
		
		session.setAttribute("user_id", "ccc333");
		Object user_id = session.getAttribute("user_id");
		
		model.addAttribute("userVO", userService.getUserInfo2(user_id));
		
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

	@PostMapping("/audApplyForm")
	public String audApplyForm(AuditionVO vo, RedirectAttributes ra,
			@RequestParam("file") List<MultipartFile> list ) {
		
System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
System.out.println(vo);
System.out.println(list);
		
		//선택되지 않은 파일 태그는 제거
		list = list.stream()
				   .filter((v) -> v.isEmpty() == false)
				   .collect(Collectors.toList());
				
		for(MultipartFile file : list) {
			if(file.getContentType().contains("image") == false && file.getContentType().contains("video")) {
				ra.addFlashAttribute("msg", "이미지 파일만 등록 가능합니다.");
				
				return "redirect:/audition/audition_notice";
			}
		}
		
		//오디션 접수(ServiceImpl)
		int result = auditionService.registAud(vo, list);
		
		String msg = (result == 1) ? "정상적으로 접수 처리되었습니다." : "오디션 접수에 실패했습니다.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/audition/audition_notice";
	}	
	

}
