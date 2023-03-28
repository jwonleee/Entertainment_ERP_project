package com.y4j.final_project;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.service.orderservice.OrderMapper;

@SpringBootTest
public class DummyCode {
	
	@Autowired
	OrderMapper orderMapper;
	
//	@Test
//	public void testCode01() {
//		for(int i=2;i<=150;i+=2) {
//			Admin_orderVO vo=Admin_orderVO.builder()
//							.admin_order_album_no(9999-i)
//							.admin_order_company(i+"회사")
//							.admin_order_prod_name("테스트네임"+i)
//							.admin_order_id("sale")
//							.admin_order_price(i*100000+"")
//							.admin_order_category("앨범")
//							.admin_order_prod_cnt(i*10000+"")
//							.admin_order_prod_no(0)
//							.admin_order_regdate("2023-03-17")
//							.admin_order_sizetype("미니").build();
//							
//			orderMapper.additionalRegist(vo);
//		}
//	}
	
//	@Test
//	public void testCode02() {
//		for(int i=1;i<=150;i++) {
//			OrderHistoryVO vo = OrderHistoryVO.builder()
//								.user_id("testId")
//								.prod_no(9999-i)
//								.order_prod_name("테스트네임"+i)
//								.order_prod_no("230319"+i)
//								.order_prod_cnt(Integer.toString(1000*i))
//								.order_date("2023-03-20")
//								.order_total_price(i*20)
//								.order_addressee("유희왕")
//								.order_zipcode(Integer.toString(99999-i))
//								.order_shipping_address("서울특별시 강남구")
//								.order_addressee_contact("010-1234-5678")
//								.order_shipping_message("부재중 시 연락주세요")
//								.order_payment_method("카드결제")
//								.order_state("주문완료").build();
//								
//				orderMapper.dummyhistory(vo);
//		}
//	}
}
