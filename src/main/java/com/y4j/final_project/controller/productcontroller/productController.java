package com.y4j.final_project.controller.productcontroller;

import java.io.Console;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.UserOrderVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.product.service.ProductService;
import com.y4j.final_project.util.Criteria;
//import com.y4j.final_project.util.PageVO;


@Controller
@RequestMapping("/product")
public class productController {
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;

	//상품 판매 페이지 기능 구현 (1) - 상품 목록 가져오기
	@GetMapping("/productList")
	public String productList (Model model, ProductVO vo, Criteria cri) {
		
		//All about part
		ArrayList<ProductVO> list = productService.productList(cri);
		model.addAttribute("list", list);	
		
		model.addAttribute("cri", cri);
		return "product/product_page";
		
	}
	
	//상품 상세 페이지 형식
	@GetMapping("/detail_page")
	public String productDetail(@RequestParam("prod_no") int prod_no,  Model model) {
		
		ArrayList<ProductVO> detail =  productService.productDetailList(prod_no);
		model.addAttribute("detail", detail);
	
	
		return "product/productDetail_page";
	}
	
	//구매 페이지
	@GetMapping("/buy_page")
	public String userOrderRightNow(Model model, UserOrderVO vo1) {
		System.out.println(1);
		System.out.println(vo1.toString());
		ArrayList<UserOrderVO> user_order =  productService.userOrderRightNow(vo1);
		System.out.println(2);
		System.out.println(user_order); 
		model.addAttribute("user_order", user_order);
		return "product/product_buy_rightnow";
	}
	
	
	//------------------ 연예인 별 상품 페이지 - 배우 -------------------
	// 채수빈 페이지
	@GetMapping("/CHAESOOBIN")
	public String soobin(Model model, Criteria cri) {
		
		ArrayList<ProductVO> soobin_list =  productService.productList_soobin(cri);
		model.addAttribute("soobin_list", soobin_list);
		model.addAttribute("cri", cri);
		
		return "product/productDetail_chaesoobin_page";
	}
	
	//이도현 페이지
	@GetMapping("/LEEDOHYUN")
	public String dohyun(Model model, Criteria cri) {
		
		ArrayList<ProductVO> dohyun_list =  productService.productList_soobin(cri);
		model.addAttribute("dohyun_list", dohyun_list);
		model.addAttribute("cri", cri);
		
		return "product/productDetail_leedohyun_page";
	}
	
	
	//-------------------- 연예인 별 상품 페이지 - 가수 ---------------------//
	
	//블랙핑크 페이지
	@GetMapping("/BLACKPINK")
	public String blackpink(Criteria cri, Model model ) {
		
		ArrayList<ProductVO> blackpink_list =  productService.productList_blackpink(cri);
		model.addAttribute("blackpink_list", blackpink_list);
		model.addAttribute("cri", cri);
		
		return "product/productDetail_blackpink_page";
	}
	
	//아이브 페이지
	@GetMapping("/IVE")
	public String ive(Criteria cri, Model model) {
		
		ArrayList<ProductVO> ive_list =  productService.productList_ive(cri);
		model.addAttribute("ive_list", ive_list);
		model.addAttribute("cri", cri);
		
		return "product/productDetail_ive_page";
	}
	
	//뉴진스 페이지
	@GetMapping("/NEWJEANS")
	public String newjeans(Criteria cri, Model model) {
		
		ArrayList<ProductVO> newjeans_list =  productService.productList_newjeans(cri);
		model.addAttribute("newjeans_list", newjeans_list);
		model.addAttribute("cri", cri);
		
		return "product/productDetail_newjeans_page";
	}
	
}