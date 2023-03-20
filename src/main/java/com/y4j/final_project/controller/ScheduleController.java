//package com.y4j.final_project.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.joda.time.LocalDate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.y4j.final_project.command.EntertainerScheduleVO;
//import com.y4j.final_project.command.EntertainerVO;
//import com.y4j.final_project.command.ScheduleVO;
//import com.y4j.final_project.schedule.service.ScheduleService;
//import com.y4j.final_project.util.Criteria;
//import com.y4j.final_project.util.PageVO;
//
//@Controller
//@RequestMapping("/schedule")
//public class ScheduleController {
//	
//	@Autowired
//	@Qualifier("scheduleService")
//	private ScheduleService scheduleService;
//	
//	@GetMapping("/admin_scheduleReg")
//	public String reg() {	
//		return "schedule/admin_scheduleReg";
//	}
//	
//	//일정 리스트
//	//////////////////////권한 처리 해야함
//	@GetMapping("/admin_scheduleList")
//	public String list(Model model,
//					   Criteria cri) {
//		System.out.println(cri.toString());
//		ArrayList<ScheduleVO> list = scheduleService.getList(cri);
//		model.addAttribute("list",list);
//		
//		//하루 일정
//		LocalDate now = LocalDate.now();
//		int todayCnt = scheduleService.getTodayScheduleCnt(now);
//		model.addAttribute("todayCnt", todayCnt);
//		
//		//pagination 처리
//		int total = scheduleService.getScheduleTotal(cri);
//		PageVO pageVO = new PageVO(cri, total);
//		System.out.println(pageVO.toString());
//		model.addAttribute("pageVO", pageVO);
//
//		return "schedule/admin_scheduleList";
//	}
//	
//	//일정 상세
//	@GetMapping("/admin_scheduleDetail")
//	public String getDetail(@RequestParam("schedule_no") int schedule_no,
//							Model model) {
//		ScheduleVO vo = scheduleService.getDetail(schedule_no);
//		System.out.println(vo.toString());
//		model.addAttribute("vo", vo);
//		
//		return "schedule/admin_scheduleDetail";
//	}
//	
//	//등록 요청
//	@PostMapping("/registForm")
//	public String registForm(ScheduleVO vo,
//							 EntertainerScheduleVO vo2,
//							 RedirectAttributes ra) {
//		
//		int result = scheduleService.regist(vo);
//		int result2 = scheduleService.registMap(vo2);
//		
//		String msg = result == 1 ? (result2 == 1 ? "일정이 등록되었습니다." : "일정 등록에 실패했습니다.") : "일정 등록에 실패하였습니다.";
//		ra.addFlashAttribute("msg", msg);
//
//		return "redirect:/schedule/admin_scheduleList";
//	}
//	
//	//일정 수정
//	@PostMapping("/modifyForm")
//	public String modifyForm(ScheduleVO vo,
//							 RedirectAttributes ra) {	
//		scheduleService.scheduleModify(vo);
//		
//		return "redirect:/schedule/admin_scheduleDetail?schedule_no=" + vo.getSchedule_no();
//	}
//	
//	// 일정 삭제
//	@PostMapping("/deleteForm")
//	public String deleteForm(@RequestParam("schedule_no") int schedule_no,
//														  RedirectAttributes ra) {
//		int result = scheduleService.scheduleDelete(schedule_no);
//		String msg = result == 1 ? "삭제되었습니다" : "삭제에 실패했습니다";
//		ra.addFlashAttribute("msg", msg);
//		
//	return "redirect:/schedule/admin_scheduleList";	
//	}
//	
//	
//	
//
//	
//
//}
