package com.y4j.final_project.controller.productcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oracle.net.ano.Service;

@Controller
@RequestMapping("/product")
public class productController {

	//상품 판매 페이지
	@GetMapping("/sell")
	public String sell() {
		return "product/product_page";
	}
	
	//상품 상세 페이지 형식
	@GetMapping("/detailpage")
	public String productDetail() {
		return "product/productDetail_page";
	}
	
}
