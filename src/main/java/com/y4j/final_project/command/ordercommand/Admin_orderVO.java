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
public class Admin_orderVO {
	
	private int admin_order_album_no; //fk
	private String admin_order_company;
	private String admin_order_id;
	private int admin_order_no;	//pk
	private String admin_order_price;
	private String admin_order_prod_category;
	private String admin_order_prod_cnt;
	private int admin_order_prod_no; //fk
	private Timestamp admin_order_regdate;
	private String admin_order_sizetype;

}
