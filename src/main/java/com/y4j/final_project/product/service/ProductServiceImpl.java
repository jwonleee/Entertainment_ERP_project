package com.y4j.final_project.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.UserOrderVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductMapper productMapper;
	
	//전체 상품 목록
	public ArrayList<ProductVO> productList(Criteria cri){
		return productMapper.productList(cri);
	}
	
	//상품 상세 페이지 
	public ArrayList<ProductVO> productDetailList(int prod_no){
		return productMapper.productDetailList(prod_no);
	}
	
	//상품 상세 페이지 -> 결제 페이지
	@Override
	public ArrayList<UserOrderVO> userOrderRightNow(UserOrderVO vo1) {
		return productMapper.userOrderRightNow(vo1);
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
