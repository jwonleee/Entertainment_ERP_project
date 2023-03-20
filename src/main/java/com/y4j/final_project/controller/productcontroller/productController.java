package com.y4j.final_project.controller.productcontroller;

import java.io.Console;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		ArrayList<ProductVO> list = productService.productList(vo);
		model.addAttribute("list", list);
	
		
		
		//최신순 기준으로 상품 목록 출력 기능
		ArrayList<ProductVO> list1 = productService.uptoDate(vo);
		model.addAttribute("list1", list1);

		
		//페이지네이션 구현
		int total = productService.pageTotal(cri);
		//pageVO 생성 
		//PageVO pageVO = new PageVO(cri, total);
		//model.addAttribute("pageVO", pageVO);
		
		return "product/product_page";
		
	}
	
	
	
	//상품 상세 페이지 형식
	@GetMapping("/detail_page")
	public String productDetail() {
		return "product/productDetail_page";
	}
	
	//구매 페이지
	@GetMapping("/buy_page")
	public String buy() {
		return "product/product_buy_rightnow";
	}
	
	//------------------ 연예인 별 상품 페이지 - 배우 -------------------
	// 채수빈 페이지
	@GetMapping("/CHAESOOBIN")
	public String soobin() {
		return "product/productDetail_chaesoobin_page";
	}
	
	//이도현 페이지
	@GetMapping("/LEEDOHYUN")
	public String dohyun() {
		return "product/productDetail_leedohyun_page";
	}
	
	
	//-------------------- 연예인 별 상품 페이지 - 가수 ---------------------//
	
	//블랙핑크 페이지
	@GetMapping("/BLACKPINK")
	public String blackpink(ProductVO vo1, Model model ) {
		
		ArrayList<ProductVO>list2 = productService.blackpink_allList(vo1);
		model.addAttribute("list2", list2);
		
		ArrayList<ProductVO>list3 = productService.blackpink_uptodateList(vo1);
		model.addAttribute("list3", list3);
		
		
		
		return "product/productDetail_blackpink_page";
	}
	
	//아이브 페이지
	@GetMapping("/IVE")
	public String ive() {
		return "product/productDetail_ive_page";
	}
	
	//뉴진스 페이지
	@GetMapping("/NEWJEANS")
	public String actor() {
		return "product/productDetail_newjeans_page";
	}
	
}
