package com.y4j.final_project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderHistoryVO {
	/*
	 * CREATE TABLE `order_history` ( 
	 * `order_no` INT NOT NULL AUTO_INCREMENT,
	 * `user_id` VARCHAR(45) NOT NULL, 
	 * `order_prod_no` INT NOT NULL, 
	 * `order_date`VARCHAR(45) NULL, 
	 * `order_cnt` INT NOT NULL DEFAULT 1,
	 * `order_total_price`VARCHAR(45) NOT NULL,
	 * `order_shipping_address` VARCHAR(200) NOT NULL,
	 * `order_state` VARCHAR(45) NOT NULL,
	 *  `order_addressee` VARCHAR(45) NOT NULL,
	 * PRIMARY KEY (`order_no`));
	 * 
	 */
	
	private int order_no ; 
	private String user_id;
	private String order_prod_name; //주문한 상품명
	private String order_prod_no; //상품마다 나오는 주문번호
	private String order_prod_cnt; // 각 상품의 구매한 갯수
	private String order_cnt; // 총 구매한 상품 갯수
	private String order_date;
	private String order_total_price;
	private String order_shipping_address;
	private String order_state;
	private String order_addressee;
	
}
