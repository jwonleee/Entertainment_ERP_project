package com.y4j.final_project.product.service;

import java.util.ArrayList;
import java.util.List;

import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.UserOrderVO;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.MoreVO;

public interface ProductService {

	//전체 상품 목록
	public ArrayList<ProductVO> productList(Criteria cri, MoreVO vo);
	public int getProdMore();
	
	//상품 상세 페이지 
	public ArrayList<ProductVO> productDetailList(int prod_no);
	
	//상품 상세 페이지 -> 결제 페이지
	public ArrayList<UserVO> userOrderRightNow(UserVO vo);
	
	//결제페이지 
	public int user_order(OrderHistoryVO vo);
	
	//결제 페이지 -> 결제 내역 페이지
	public ArrayList<OrderHistoryVO> user_orderList(OrderHistoryVO vo, Criteria cri);
	public int getProdOrderTotal(Criteria cri); 
	
	//결제 내역 상세 페이지
	public ArrayList<OrderHistoryVO> user_orderList_detail(String order_prod_no);
	
	//블랙핑크 페이지-전체 상품 
	public ArrayList<ProductVO> productList_blackpink(Criteria cri);
	
	//아이브 전체 상품
	public ArrayList<ProductVO> productList_ive(Criteria cri);
	
	//뉴진스 전체 상품
	public ArrayList<ProductVO> productList_newjeans(Criteria cri);
	
	//이도현 전체 상품
	public ArrayList<ProductVO> productList_dohyun(Criteria cri);
	
	//채수빈 전체 상품
	public ArrayList<ProductVO> productList_soobin(Criteria cri);

	
	
	
}
