package com.y4j.final_project.command.ordercommand;


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
	private String album_artist;
	private String album_title;
	private String album_category;
	private String album_price;
	private String album_release_date;
	private Integer album_discount_rate;
	private Integer album_stock;
	private String album_version;
	private String album_img_path;
	private Integer album_hit;
	private Integer album_sales_volume;
}
