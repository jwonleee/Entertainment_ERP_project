package com.y4j.final_project.command.ordercommand;


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
public class Admin_orderVO {
	
	private Integer admin_order_no;	//pk
	private Integer admin_order_album_no; //fk
	private Integer admin_order_prod_no; //fk
	/*
	 * @NotNull -null값만 허용하지 않음(wrapper의 Integer, Long, String 등등)
	 * @NotBlank -null값과 공백을 허용하지 않음(String에만 적용)
	 * @NotEmpty - null값을 허용하지 않음(Array, List 등에 적용)
	 * @Pattern -정규표현식에 맞는 문자열을 정의할 수 있음(String에만 적용)
	 * 
	 * @Email -이메일형식검증(공백은 통과)
	 * @Min -최소값
	 * @Max -최대값
	 * 
	 * @NotNull(message="")
	 * @Pattern(regexp="정규표현식",message="")
	 * @Email(message="")
	 */
	
	@NotBlank(message="로그인되지 않았습니다.")
	private String admin_order_id;
	
	@NotNull(message="정상등록되지 않았습니다. 재등록해주세요.")
	private String admin_order_price;
	
	@NotBlank(message="상세카테고리를 적용하고 상세발주조회버튼을 눌러주세요.")
	private String admin_order_category;
	
	@NotNull(message="정상등록되지 않았습니다. 재등록해주세요.")
	private String admin_order_prod_cnt;
	
	@NotBlank(message="발주일자는 필수입니다.")
	private String admin_order_regdate;
	
	@NotBlank(message="정상등록 실패! 재등록해주세요.")
	private String admin_order_sizetype;
	
	@NotBlank(message="회사는 필수입니다.")
	private String admin_order_company;
	
	@NotBlank(message="정상등록 실패! 재등록해주세요.")
	private String admin_order_prod_name; //화면에서 보여줄 앨범/상품명
	
}
