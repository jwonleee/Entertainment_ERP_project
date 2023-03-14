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

}

