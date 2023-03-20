package com.y4j.final_project.controller.ordercontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.command.ordercommand.AlbumVO;
import com.y4j.final_project.command.ordercommand.CategoryVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.service.orderservice.OrderService;

@RestController
public class OrderAjaxController {

	@Autowired
	private OrderService orderService;
	
	//대분류 카테고리 요청
	@GetMapping("/get_category")
	public ArrayList<CategoryVO> getCategory(){
		return orderService.getCategory();
	}
	
	//중, 소분류 카테고리 요청
	@GetMapping("/get_category_child/{category_group_id}/{category_lv}/{category_detail_lv}")
	public ArrayList<CategoryVO> getCategoryChild(@PathVariable("category_group_id")String category_group_id,@PathVariable("category_lv")int category_lv,@PathVariable("category_detail_lv")int category_detail_lv){
		CategoryVO vo = CategoryVO.builder().category_group_id(category_group_id).category_lv(category_lv).category_detail_lv(category_detail_lv).build();
		return orderService.getCategoryChild(vo);
	}
	
	//모든 중분류 카테고리 요청
	@GetMapping("/get_mid_category")
	public ArrayList<CategoryVO> getMidCategory(){
		return orderService.getMidCategory();
	}
	
	//상세조회
	@GetMapping("/get_admin/{admin_order_no}")
	public Admin_orderVO getAdmin(@PathVariable("admin_order_no")String admin_order_no) {
		return orderService.getDetail(admin_order_no);
	}
	
	@GetMapping("/get_album/{admin_order_album_no}")
	public AlbumVO getAlbum(@PathVariable("admin_order_album_no")String admin_order_album_no) {
		return orderService.getAlbum(admin_order_album_no);
	}
	
	@GetMapping("/get_product/{admin_order_prod_no}")
	public ProductVO getProduct(@PathVariable("admin_order_prod_no")String admin_order_prod_no) {
		return orderService.getProduct(admin_order_prod_no);
	}
	
	
	//소분류 카테고리 등록
	@PostMapping("/small_category_reg")
	public Integer smallCategoryReg(@RequestParam("category_group_id")String category_group_id, @RequestParam("category_detail_parent_lv")String category_detail_parent_lv,@RequestParam("category_detail_nm")String category_detail_nm) {
		CategoryVO vo = CategoryVO.builder().category_group_id(category_group_id).category_lv(3).category_nm("소분류").category_detail_nm(category_detail_parent_lv).category_detail_nm(category_detail_nm).category_parent_lv(2).category_detail_parent_lv(Integer.parseInt(category_detail_parent_lv)).build();
		return orderService.midSmallCategoryReg(vo);
	}
}
