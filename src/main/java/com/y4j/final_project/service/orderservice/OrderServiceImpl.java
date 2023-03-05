package com.y4j.final_project.service.orderservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
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

	
	

	
}
