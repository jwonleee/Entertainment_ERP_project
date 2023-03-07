package com.y4j.final_project.command.ordercommand;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVO {
	
	private int prod_no;
	private String prod_type;
	private String prod_name;
	private String prod_category;
	private String prod_sizetype;
	private String prod_artist;
	private String prod_price;
	private int prod_discount_rate;
	private int prod_stock;
	private String prod_img_path;
	private String prod_info_img_path;
	private Timestamp prod_regdate;
	
	
}
