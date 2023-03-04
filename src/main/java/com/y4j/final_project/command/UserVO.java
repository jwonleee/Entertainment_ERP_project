package com.y4j.final_project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

	private String user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_contact;
	private String user_address;
	private String user_email;
	private String user_gender;
}
