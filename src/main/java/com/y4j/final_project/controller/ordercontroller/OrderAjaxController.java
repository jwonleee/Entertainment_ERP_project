package com.y4j.final_project.controller.ordercontroller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
	
	//특정 중분류에 속한 소분류 카테고리의 디테일 이름
	@PostMapping("/get_small_category")
	public ArrayList<String> getSmallCategory(HttpServletRequest req){
		ArrayList<String> result = new ArrayList<>();//success콜백에 보낼 배열
		
		String[]lang = req.getParameterValues("midArr");
		for(int i=0;i<lang.length;i++) {
			//System.out.println(lang[i]);
			JsonParser parser = new JsonParser(); //lang[i]는 json문자열 형태. 파싱 필요
			try {
				JsonObject obj=(JsonObject)parser.parse(lang[i]);
				String category_group_id=obj.get("category_group_id").toString().replaceAll("\"", "");
				int category_detail_lv=Integer.parseInt(obj.get("category_detail_lv").toString());
				CategoryVO vo=CategoryVO.builder().category_group_id(category_group_id).category_detail_lv(category_detail_lv).build();//vo 생성
				result.addAll(orderService.getSmallCategory(vo));//기본배열에 내용 합치기
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//end for
		return result;
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
