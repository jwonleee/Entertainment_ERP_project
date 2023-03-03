package com.y4j.final_project.command;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AwsS3 {
	
	private String key;
	private String path;
	
	@Builder
	public AwsS3(String key, String path) {
		this.key=key;
		this.path=path;
	}
	
}
