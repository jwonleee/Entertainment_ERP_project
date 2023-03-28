package com.y4j.final_project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderVO {
	
	private int user_order_no;
	private String user_id;
	private int user_order_prod_cnt;
	private int user_order_total_price;
	private String user_order_prod_name;
	private int user_order_prod_no;
	private String user_order_prod_img;
	private String user_discount_rate;
	private String user_delivery_fee;
}
