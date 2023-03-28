package com.y4j.final_project.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.y4j.final_project.command.CartListVO1;
import com.y4j.final_project.command.CartVO;
import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.UserOrderVO;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.MoreVO;

@Mapper
public interface ProductMapper {
	
	//전체 상품 목록
	public ArrayList<ProductVO> productList(@Param("cri")Criteria cri, @Param("vo")MoreVO vo);
	public int getProdMore();
	
	//상품 상세 페이지 
	public ArrayList<ProductVO> productDetailList(int prod_no);
	
	//상품 상세 페이지 -> 결제 페이지
	public ArrayList<UserVO> userOrderRightNow(UserVO vo);

	//장바구니 리스트 
	public ArrayList<CartVO> prod_cartList(CartVO vo);
	
	//결제페이지 등록
	public int user_order(OrderHistoryVO vo);
	
	//결제 페이지 -> 결제 내역 페이지
	public ArrayList<OrderHistoryVO> user_orderList(OrderHistoryVO vo, @Param("cri") Criteria cri);
	public int getProdOrderTotal(Criteria cri);
	
	//결제 내역 상세 페이지
	public ArrayList<OrderHistoryVO> user_orderList_detail(@Param("order_prod_no") String order_prod_no);
	
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
