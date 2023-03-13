package com.y4j.final_project.controller.ordercontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//중, 소분류 카테고리 요청
	@GetMapping("/getCategoryChild/{category_group_id}/{category_lv}/{category_detail_lv}")
	public ArrayList<CategoryVO> getCategoryChild(@PathVariable("category_group_id")String category_group_id,@PathVariable("category_lv")int category_lv,@PathVariable("category_detail_lv")int category_detail_lv){
		CategoryVO vo = CategoryVO.builder().category_group_id(category_group_id).category_lv(category_lv).category_detail_lv(category_detail_lv).build();
		return orderService.getCategoryChild(vo);
	}
	
	
	
	
	
	
}
