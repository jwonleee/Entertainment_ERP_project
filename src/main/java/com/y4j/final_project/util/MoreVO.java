package com.y4j.final_project.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MoreVO {

	private int startIndex; 
	private	int endIndex;
	private	int prod_total;
	
	public MoreVO() {
		this.startIndex = 0;
		this.prod_total = 12;
	}
	
}
