package com.y4j.final_project.command.ordercommand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin_orderVO {
	
	private Integer admin_order_no; // pk
	private Integer admin_order_album_no; // fk
	private Integer admin_order_prod_no; // fk
	private String admin_order_id;
	private String admin_order_price;
	private String admin_order_category;
	private String admin_order_prod_cnt;
	private String admin_order_regdate;
	private String admin_order_sizetype;
	private String admin_order_company;
	private String admin_order_prod_name; // 화면에서 보여줄 앨범/상품명
	
}
