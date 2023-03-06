package com.y4j.final_project.controller.ordercontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.y4j.final_project.command.ordercommand.CategoryVO;
import com.y4j.final_project.service.orderservice.OrderService;

@RestController
public class OrderAjaxController {

	@Autowired
	private OrderService orderService;
	
	//대분류 카테고리 요청
	@GetMapping("/getCategory")
	public ArrayList<CategoryVO> getCategory(){
		return orderService.getCategory();
	}
	
}
