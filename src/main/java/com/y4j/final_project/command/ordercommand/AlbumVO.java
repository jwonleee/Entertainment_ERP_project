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
public class AlbumVO {
	
	private Integer album_no;
	
	@NotBlank(message="아티스트는 필수입니다.")
	private String album_artist;
	
	@NotBlank(message="앨범명은 필수입니다.")
	private String album_title;
	
	@NotBlank(message="앨범 카테고리를 적용하고 상세발주조회버튼을 눌러주세요.")
	private String album_category;
	
	@NotBlank(message="가격을 입력하세요")
	@Min(value=1000,message="가격은 1000원 이상입니다.")
	private String album_price;
	
	@NotBlank(message="발매일을 입력하세요.")
	private String album_release_date;
	
	@Max(value=99, message="할인률은 최대 99%입니다.")
	@NotNull(message="할인률을 입력해 주세요.")
	private Integer album_discount_rate;
	
	@NotNull(message="재고를 입력하세요")
	@Min(value=100, message="재고는 최소 100개 이상입니다.")
	private Integer album_stock;
	
	@NotBlank(message="앨범버전을 입력하세요. 기본값은 싱글입니다.")
	private String album_version;
	
	@NotBlank(message="이미지는 필수입니다.")
	private String album_img_path;
	
	private Integer album_hit;
	private Integer album_sales_volume;
}
