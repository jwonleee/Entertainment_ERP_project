package com.y4j.final_project.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.y4j.final_project.aws.service.AwsS3Service;
import com.y4j.final_project.command.AwsS3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;
	
	
    @PostMapping("/resource")
    public AwsS3 upload(@RequestPart("file") MultipartFile multipartFile) throws IOException {

    	return awsS3Service.upload(multipartFile,"upload");
    }
    
    @PostMapping("/resource2")
    public Map<String,AwsS3> upload2(@RequestPart("file") MultipartFile multipartFile, @RequestPart(value="PDfile", required = false) MultipartFile multipartFile2) throws IOException {
    		Map<String, AwsS3> map=new HashMap<>();
    		map.put("pimg",awsS3Service.upload(multipartFile, "upload"));
    		map.put("pdimg",awsS3Service.upload(multipartFile2, "upload"));
        
    	return map;
    }

    @DeleteMapping("/resource")
    public void remove(AwsS3 awsS3) {
        awsS3Service.remove(awsS3);
    }
    
    @RequestMapping("/show")
    public String imgSrc() {
    	return awsS3Service.getThumbnailPath("upload/fall.jpg");
    }
}