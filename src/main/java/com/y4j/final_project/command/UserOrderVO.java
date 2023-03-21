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
	private String user_order_date;
	private int user_order_prod_cnt;
	private String user_order_total_price;
	private String user_order_prod_name;
	private String user_order_prod_img;
}
