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
public class MessageVO {

	private int msg_no;  //default : auto increment
	private int msg_writer_no;
	private String msg_writer_id;
	private String msg_writer_name;
	private int msg_receiver_no;
	private String msg_receiver_id;
	private String msg_receiver_name;
	private String msg_title;
	private String msg_content;
	private LocalDateTime msg_senddate;  //default : current_timestamp
	
}