package com.y4j.final_project.controller.productcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.command.CartListVO1;
import com.y4j.final_project.command.CartVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.product.service.ProductService;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.MoreVO;

@RestController

public class productAjaxController {
	 
	 
	 @Autowired
	 @Qualifier("productService") private ProductService productService;
	 
	@PostMapping("/getProduct")
	public ArrayList<ProductVO> getProdMore(@RequestParam("endIndex") int endIndex ,MoreVO vo, Criteria cri) {
	
		/*
		 * vo.setEndIndex(productService.getProdMore()); vo.setStartIndex(12);
		 */
	
		 int plus = 12; 
		 for(int i =1; i <=(vo.getEndIndex()/10); i++) {
			 vo.setStartIndex((plus * i) + vo.getStartIndex());
			 vo.setProd_total(productService.getProdMore());
		  }
	 
		 
		return productService.productList(cri, vo);
	}
	
	

}
