package com.y4j.final_project.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityVO {

	private int authority_mng_no;  //default : auto increment
	private int authority_mng_admin_no;
	private String authority_mng_admin_id;
	private String authority_mng_admin_name;
	private String authority_mng_admin_original_type;  //default : none
	private String authority_mng_admin_apply_type;
	private String ent_name;
	private LocalDateTime authority_mng_apply_date;  //default: current_timestamp
	private LocalDateTime authority_mng_authorize_date;  //default: current_timestamp
}