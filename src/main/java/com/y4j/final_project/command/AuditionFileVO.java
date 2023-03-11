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
public class AuditionFileVO {

	private int audition_cv_upload_no;
	private int audition_cv_no;
	private String audition_cv_file_path;
	private LocalDateTime audition_cv_file_regdate;
	
}
