package com.y4j.final_project;import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.y4j.final_project.service.AwsS3Service;

@SpringBootTest
public class TestCode {
	
	@Autowired
	private AwsS3Service awsS3Service;
	
	
	@Test
	public void findImg() {
		String imgPath=awsS3Service.getThumbnailPath("NewJeans-test1.jpeg");
		System.out.println(imgPath);
	}
}
