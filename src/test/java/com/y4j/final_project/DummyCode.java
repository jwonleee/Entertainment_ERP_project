package com.y4j.final_project;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.service.orderservice.OrderMapper;

@SpringBootTest
public class DummyCode {
	
	@Autowired
	OrderMapper orderMapper;
	
//	@Test
//	public void testCode01() {
//		for(int i=1;i<=200;i++) {
//			Admin_orderVO vo=Admin_orderVO.builder()
//							.admin_order_album_no(i)
//							.admin_order_company(i+"회사")
//							.admin_order_id("orderadministrator")
//							.admin_order_price(i*100000+"")
//							.admin_order_prod_category("카테고리화")
//							.admin_order_prod_cnt(i*10000+"")
//							.admin_order_prod_no(9999-i)
//							.admin_order_regdate(new Timestamp(System.currentTimeMillis()))
//							.admin_order_sizetype("free").build();
//							
//			orderMapper.testRegist(vo);
//		}
//	}
}
