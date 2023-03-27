package com.y4j.final_project.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderHistoryVO {
	
	
	private int order_no ; 
	private String user_id;
	private int prod_no;
	private String order_prod_name; //주문한 상품명
	private String order_prod_no; //상품마다 나오는 주문번호
	private String order_prod_cnt; // 각 상품의 구매한 갯수
	private String order_cnt; // 총 구매한 상품 갯수

	private String order_date; // 주문한 날짜
	private int order_total_price; //총 결제 가격
	
	@NotBlank (message = "이름을 입력하세요")
	private String order_addressee; //수신인
	
	private String order_zipcode; //수신인 우편번호
	@NotBlank (message = "주소를 입력하세요")
	private String order_shipping_address; //배송지
	
	@NotBlank (message = "휴대전화를 입력하세요")
	private String order_addressee_contact; //수신인 연락처
	private String order_shipping_message; //배송 메시지
	
	@NotBlank (message = "결제수단을 선택해주세요")
	private String order_payment_method; //결제 수단
	
	private String order_state; //주문 상태	
}
