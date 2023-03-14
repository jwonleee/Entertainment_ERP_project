package com.y4j.final_project.product.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	//상품 판매 페이지(전체 상품 목록)
	@Override
	public ArrayList<ProductVO> productList(ProductVO vo) {
	
		return productMapper.productList(vo);
	}

	@Override
	public ArrayList<ProductVO> uptoDate(ProductVO vo) {
		
		return productMapper.uptoDate(vo);
	}

	@Override
	public int pageTotal(Criteria cri) {
	
		return 0;
	}
	
	///////////////////////////////////////////////////////////////////
	
	//====================블랙핑크 페이지========================//
	
	//블랙핑크 전체상품 목록
	@Override
	public ArrayList<ProductVO> blackpink_allList(ProductVO vo1) {
	
		return productMapper.blackpink_allList(vo1);
	}
	
	//블랙핑크 최신순 상품 목록
	@Override
	public ArrayList<ProductVO> blackpink_uptodateList(ProductVO vo1) {
		// TODO Auto-generated method stub
		return productMapper.blackpink_uptodateList(vo1);
	}
	
	//블랙핑크 판매량순 상품 목록
	@Override
	public ArrayList<ProductVO> blackpink_popularList(ProductVO vo1) {
		// TODO Auto-generated method stub
		return null;
	}

}

