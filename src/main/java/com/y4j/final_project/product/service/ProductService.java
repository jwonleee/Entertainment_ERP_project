package com.y4j.final_project.product.service;

import java.util.ArrayList;

import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;

public interface ProductService {

	
	
	//상품 판매 페이지(전체 상품 목록)
	public ArrayList<ProductVO> productList(ProductVO vo);
	
	//상품 최신순
	public ArrayList<ProductVO> uptoDate(ProductVO vo);
	
	
	//상품 판매량순

	///////////////////////////////////////////////////////////////////
	
	//페이지네이션
	public int pageTotal(Criteria cri);
	
	///////////////////////////////////////////////////////////////////
	
	//====================블랙핑크 페이지========================//
	//블랙핑크 전체상품 목록
	public ArrayList<ProductVO> blackpink_allList (ProductVO vo1);
	
	//블랙핑크 최신순 상품 목록
	public ArrayList<ProductVO> blackpink_uptodateList (ProductVO vo1);
	
	
	//블랙핑크 판매량순 상품 목록
	public ArrayList<ProductVO> blackpink_popularList (ProductVO vo1);
	
	
	
}
