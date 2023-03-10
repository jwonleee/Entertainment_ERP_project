package com.y4j.final_project.service.orderservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.command.ordercommand.AlbumVO;
import com.y4j.final_project.command.ordercommand.CategoryVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;

	
	
	@Override
	public int getOrderTotal(String user_id, Criteria cri) {
		return orderMapper.getOrderTotal(user_id, cri);
	}
	
	@Override
	public ArrayList<Admin_orderVO> getOrderList(String user_id,Criteria cri) {
		return orderMapper.getOrderList(user_id,cri);
	}

	@Override
	public Admin_orderVO getDetail(String admin_order_no) {
		return orderMapper.getDetail(admin_order_no);
	}

	@Override
	public int albumRegist(AlbumVO vo) {
		return orderMapper.albumRegist(vo);
	}
	
	@Override
	public int productRegist(ProductVO vo) {
		return orderMapper.productRegist(vo);
	}
	
	@Override
	public int adminAlbumRegist(Admin_orderVO admvo) {
		return orderMapper.adminAlbumRegist(admvo);
	}

	@Override
	public int adminProductmRegist(Admin_orderVO admvo) {
		return orderMapper.adminProductmRegist(admvo);
	}
	
	
	@Override
	public ArrayList<CategoryVO> getCategory() {
		return orderMapper.getCategory();
	}

	@Override
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo) {
		return orderMapper.getCategoryChild(vo);
	}




	
	

	
}
