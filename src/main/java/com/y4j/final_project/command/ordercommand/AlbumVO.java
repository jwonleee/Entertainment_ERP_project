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
public class AlbumVO {
	
	private int album_no;
	private String album_artist;
	private String album_title;
	private String album_category;
	private String album_price;
	private Timestamp album_release_date;
	private int album_discount_rate;
	private int album_stock;
	private String album_img_path;
}
