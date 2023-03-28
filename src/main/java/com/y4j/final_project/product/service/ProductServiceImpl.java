package com.y4j.final_project.product.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.CartListVO1;
import com.y4j.final_project.command.CartVO;
import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.UserOrderVO;
import com.y4j.final_project.command.UserVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;
import com.y4j.final_project.util.MoreVO;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductMapper productMapper;
	
	//전체 상품 목록
	public ArrayList<ProductVO> productList(Criteria cri, MoreVO vo){
		return productMapper.productList(cri, vo);
	}
	
	@Override
	public int getProdMore() {
		return productMapper.getProdMore();
	}

	
	//상품 상세 페이지 
	public ArrayList<ProductVO> productDetailList(int prod_no){
		return productMapper.productDetailList(prod_no);
	}
	
	//상품 상세 페이지 -> 결제 페이지
	@Override
	public ArrayList<UserVO> userOrderRightNow(UserVO vo) {
		return productMapper.userOrderRightNow(vo);
	}
	
	//상품 상세 페이지 -> 장바주니 담기
	@Override
	public int addCart(CartVO vo) {
		//주문 폼 등록 완료 여부 확인
		int result = productMapper.addCart(vo);
		return result;
	}
	
	//장바구니 리스트 뽑기
	@Override
	public ArrayList<CartVO> prod_cartList(CartVO vo) {
		return productMapper.prod_cartList(vo);
	}

	
	//결제 페이지 
	@Override
	public int user_order(OrderHistoryVO vo) {
		
		//현재 날짜 기준으로 랜덤 주문번호 생성
		Date date = new Date();
		SimpleDateFormat sdft = new SimpleDateFormat("yyyyMMdd");
		String ran = sdft.format(date);
		
		for(int i=1; i<6;i++) {
			ran += (int)( Math.floor(Math.random() * i) + 1);	
		}
		System.out.println(ran);
		
		vo.setOrder_prod_no(ran); //주문 번호 생성
	
		//주문 폼 등록 완료 여부 확인
		int result = productMapper.user_order(vo);
		return result;
	}
	
	//결제 페이지 -> 결제 내역 페이지
	@Override
	public ArrayList<OrderHistoryVO> user_orderList(OrderHistoryVO vo, Criteria cri) {
		return productMapper.user_orderList(vo, cri);
	}
	
	
	@Override
	public int getProdOrderTotal(Criteria cri) {
		return productMapper.getProdOrderTotal(cri);
	}

	//결제 내역 상세 페이지
	@Override
	public ArrayList<OrderHistoryVO> user_orderList_detail(String order_prod_no) {
		return productMapper.user_orderList_detail(order_prod_no);
	}

	
	//블랙핑크 페이지-전체 상품 
	public ArrayList<ProductVO> productList_blackpink(Criteria cri){
		return productMapper.productList_blackpink(cri);
	}
	
	//아이브 전체 상품
	public ArrayList<ProductVO> productList_ive(Criteria cri){
		return productMapper.productList_ive(cri);
	}
	
	//뉴진스 전체 상품
	public ArrayList<ProductVO> productList_newjeans(Criteria cri){
		return productMapper.productList_newjeans(cri);
	}
	
	//이도현 전체 상품
	public ArrayList<ProductVO> productList_dohyun(Criteria cri){
		return productMapper.productList_dohyun(cri);
	}
	
	//채수빈 전체 상품
	public ArrayList<ProductVO> productList_soobin(Criteria cri){
		return productMapper.productList_soobin(cri);
	}

	




	




	



	
}
