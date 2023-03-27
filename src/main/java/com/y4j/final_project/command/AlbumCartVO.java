package com.y4j.final_project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumCartVO {
	
	private int cart_no;
    private int cart_prod_no;
    private String album_img_path;
    private String album_artist;
   private String album_title;
    private String cart_prod_cnt;
    private int album_discount_rate;
    private String album_price;
   private int cart_total_price;
}
