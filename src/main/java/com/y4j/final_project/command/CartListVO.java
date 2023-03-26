package com.y4j.final_project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartListVO {
	
	private int cart_no; 
	private int cart_prod_no;
	private String prod_img_path;
	private String prod_artist;
	private String cart_prod_name;
	private String prod_name;
	private String cart_prod_cnt;
	private int prod_discount_rate;
	private String prod_price;
	private String user_id;

}
