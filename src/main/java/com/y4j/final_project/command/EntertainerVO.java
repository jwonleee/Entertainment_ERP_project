package com.y4j.final_project.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntertainerVO {

	private int ent_no;
	private String ent_name;
	
	private String ent_birth;
	private String ent_debut_date;
	private String ent_contract_startdate;
	private String ent_contract_enddate;
	
	private String ent_contract_cnt;
	private String ent_gender;
	private String ent_type;
	private String ent_group_name;
	private String ent_note;
	private String ent_img_path;

}
