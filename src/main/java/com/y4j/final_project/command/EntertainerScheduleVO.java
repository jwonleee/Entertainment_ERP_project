package com.y4j.final_project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntertainerScheduleVO {

	private int entertainer_schedule_no;
	private String ent_name;
	private int schedule_no;
	
}
