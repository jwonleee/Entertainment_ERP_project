package com.y4j.final_project.command.ordercommand;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotBlank(message="상품타입은 필수입니다.")
	private String prod_type;
	
	@NotBlank(message="상품명은 필수입니다.")
	private String prod_name;
	
	@NotBlank(message="상품 카테고리를 적용하고 상세발주조회버튼을 눌러주세요.")
	private String prod_category;
	
	@NotBlank(message="사이즈/타입은 필수입니다.")
	private String prod_sizetype;
	
	@NotBlank(message="연예인을 확인하세요.")
	private String prod_artist;
	
	@NotBlank(message="가격을 입력하세요")
	@Min(value=1000,message="가격은 1000원 이상입니다.")
	private String prod_price;
	
	@Max(value=99, message="할인률은 최대 99%입니다.")
	@NotNull(message="할인률을 입력해 주세요.")
	private Integer prod_discount_rate;
	
	@NotNull(message="재고를 입력하세요")
	@Min(value=100, message="재고는 최소 100개 이상입니다.")
	private Integer prod_stock;
	
	@NotBlank(message="이미지는 필수입니다.")
	private String prod_img_path;
	
	@NotBlank(message="상세설명 이미지는 필수입니다.")
	private String prod_info_img_path;
	
	@NotBlank(message="등록일을 입력하세요.")
	private String prod_regdate;
	
	private Integer prod_hit;
	private Integer prod_sales_volume;
	
	
}
