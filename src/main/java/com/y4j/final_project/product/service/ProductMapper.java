package com.y4j.final_project.product.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;

@Mapper
public interface ProductMapper {
	
	

	//상품 판매 페이지(전체 상품 목록)
	public ArrayList<ProductVO> productList(ProductVO vo);

	//상품 최신순
	public ArrayList<ProductVO> uptoDate(ProductVO vo);

	//페이지네이션
	public int pageTotal(Criteria cri);
}
