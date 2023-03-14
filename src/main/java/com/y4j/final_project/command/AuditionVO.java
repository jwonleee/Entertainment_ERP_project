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
public class AuditionVO {

	private int audition_cv_no;  //default : auto increment
	private String audition_cv_type;
	private String audition_cv_user_id;
	private String audition_cv_name;
	private String audition_cv_gender;
	private String audition_cv_age;
	private String audition_cv_height;
	private String audition_cv_weight;
	private String audition_cv_contact;
	private String audition_cv_email;
	private LocalDateTime audition_cv_regdate;  //default : current_timestamp
	private String audition_cv_progress;  //default : "1차"
	
}