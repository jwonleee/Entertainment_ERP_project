package com.y4j.final_project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartVO {

	private int cart_no; 
	private int cart_prod_no;
	private String cart_prod_img_path;
	private String cart_prod_artist;
	private String cart_prod_name;
	private String cart_prod_cnt;
	private int cart_prod_discount_rate;
	private String cart_prod_price;
	private String user_id;
	private int cart_total_price;
	//////////////////////////////////////////////////////

}
