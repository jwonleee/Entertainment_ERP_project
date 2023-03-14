package com.y4j.final_project.command.ordercommand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVO {
	
	private Integer prod_no;
	private String prod_type;
	private String prod_name;
	private String prod_category;
	private String prod_sizetype;
	private String prod_artist;
	private String prod_price;
	private Integer prod_discount_rate;
	private Integer prod_stock;
	private String prod_img_path;
	private String prod_info_img_path;
	private String prod_regdate;
	private Integer prod_hit;
	private Integer prod_sales_volume;
	
	

	
}
