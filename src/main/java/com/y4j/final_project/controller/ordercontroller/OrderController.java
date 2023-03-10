package com.y4j.final_project.controller.ordercontroller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.command.ordercommand.AlbumVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.service.orderservice.OrderService;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.PageVO;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;

	
	
	//목록화면
	@GetMapping("/orderList")
	public String orderList(HttpSession session, Model model, Criteria cri) {
		//로그인 했다고 가정
		session.setAttribute("user_id", "orderadministrator");
		String user_id=(String)session.getAttribute("user_id");
		//발주관리자가 아니라면
		if(!user_id.equals("orderadministrator")) {
			return "redirect:/admin/hold";
		}
		
		//발주리스트 가져오기
		ArrayList<Admin_orderVO> orderList=orderService.getOrderList(user_id,cri);
		model.addAttribute("orderList",orderList);
		
		
		
		//페이징 처리
		int total=orderService.getOrderTotal(user_id, cri);
		PageVO pageVO=new PageVO(cri,total);
		model.addAttribute("pageVO",pageVO);
		
		return "order/orderList";
	}
	
	/////////////////////////////////
	//상세화면
	@GetMapping("/orderDetail")
	public String orderDetail(@ModelAttribute("admin_order_no")String admin_order_no, Model model) {
		Admin_orderVO vo = orderService.getDetail(admin_order_no);
		model.addAttribute("vo",vo);
		return "order/orderDetail";
	}
	
	//추가발주
	@PostMapping("/detailOrderReg")
	public String detailOrderReg() {
		
		return "redirect:/order/orderList";
	}
	
	
	
	//////////////////////////////////
	//초기발주
	@GetMapping("/orderReg")
	public String orderReg(HttpSession session, Model model) {
		//로그인 했다고 가정
		session.setAttribute("user_id", "orderadministrator");
		String user_id=(String)session.getAttribute("user_id");
		//발주관리자가 아니라면
		if(!user_id.equals("orderadministrator")) {
			return "redirect:/admin/hold";
		}
		model.addAttribute("admin_id",user_id);
		
		
		return "order/orderReg";
	}
	
	
	@PostMapping("/registForm")
	public String registForm(Admin_orderVO avo, AlbumVO alvo, ProductVO pvo, RedirectAttributes ra) {
		//System.out.println(alvo.toString());
		//System.out.println(pvo.toString());
		//System.out.println(avo.toString());
		
		
		int result=0;
		String category=avo.getAdmin_order_category();
		if(category.equals("A5")||category.equals("A9")||category.equals("A13")) {//앨범일 때
			result+=orderService.albumRegist(alvo); //앨범에 저장
			result+=orderService.adminAlbumRegist(avo); //관리자 order에 저장
		}else{//상품일 때
			result+=orderService.productRegist(pvo); //상품에 저장
			result+=orderService.adminProductmRegist(avo); //관리자 상품에 저장
		}
		
		if(result==2) {
			String msg="성공적으로 등록되었습니다.";
			ra.addFlashAttribute("msg",msg);
			return "redirect:/order/orderList";
		}else{
			String msg="등록에 실패하였습니다.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/order/orderReg";
		}
	}
	
	
	
	
	
}
