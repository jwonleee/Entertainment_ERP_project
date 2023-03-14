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
}
