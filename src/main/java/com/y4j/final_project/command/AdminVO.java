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
public class AdminVO {

	private int admin_no;  //default : auto increment
	private String admin_type;  //default : none
	private String admin_id;
	private String admin_pw;
	private String admin_name;
	private String admin_contact;
	private String admin_address;
	private String admin_gender;
	private String admin_email;
	private String ent_name;  //default: none
	private LocalDateTime admin_regdate;  //default : current_timestamp
}