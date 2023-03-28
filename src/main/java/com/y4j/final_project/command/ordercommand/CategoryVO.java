package com.y4j.final_project.command.ordercommand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryVO {
	
	private int category_id;
	private String category_group_id;
	private int category_lv;
	private String category_nm;
	private int category_detail_lv;
	
	@NotBlank(message="상품/앨범명은 필수입니다.")
	private String category_detail_nm;
	
	private int category_parent_lv;
	private int category_detail_parent_lv;
	
}
