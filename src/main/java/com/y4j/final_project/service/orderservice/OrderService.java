package com.y4j.final_project.service.orderservice;

import java.util.ArrayList;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.command.ordercommand.CategoryVO;
import com.y4j.final_project.util.Criteria;

public interface OrderService {
	public int getOrderTotal(String user_id, Criteria cri); //전체게시글수 조회
	public ArrayList<Admin_orderVO> getOrderList(String user_id, Criteria cri); //발주목록
	public Admin_orderVO getDetail(String admin_order_no); //상세조회
	//신규등록
	//public int updateOrder();//추가발주-업데이트
	
	public ArrayList<CategoryVO> getCategory(); //대분류 카테고리
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo); //중, 소분류 카테고리
	
}
