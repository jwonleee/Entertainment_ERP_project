package com.y4j.final_project.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@Value("${project.upload.path}")
	private String uploadPath;
	

	@GetMapping("/upload01")
	public void upload01() {
		
	}
	@GetMapping("/upload_aws")
	public String upload_aws() {
		return "upload/upload_aws";
	}
	
	//현재 날짜로 폴더 생성 메서드
	public String makeDir() {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MMdd");
		String now = sdf.format(date);
		
		String path = uploadPath + "/" + now; //경로
//		String path = uploadPath2 + "/" + now; //경로 - 한글 섞인 경로는 제대로 설정되지 않음
		File file = new File(path);
		
		if(file.exists() == false) file.mkdir();  //폴더 생성
		
		return path;
	}
	
	//단일 파일 업로드
	@PostMapping("/upload_complete")
	public String uploadFile(@RequestParam("testFile") MultipartFile file) {
		
//		String origin = file.getOriginalFilename();
//		long size = file.getSize();
//		String type = file.getContentType();
		
		String originName = file.getOriginalFilename();  //IE, Edge는 전체 경로가 들어오므로 파일명만 추출
		String fileName = originName.substring(originName.lastIndexOf("/") + 1);  //서버에서 저장할 파일 이름
		String filePath = makeDir();  //날짜별 업로드 폴더 생성
		String savePath = filePath + "/" + fileName;  //최종 업로드 경로
		
		String front = savePath.substring(0, savePath.lastIndexOf("."));  //순수 파일명(winter)
		String end = savePath.substring(savePath.lastIndexOf("."), savePath.length());  //. + 확장자(.jpg)
		
//		System.out.println("originName : " + originName);  //fall.jpg
//		System.out.println("size : " + size);  //1251710
//		System.out.println("type : " + type);  //image/jpeg
//		System.out.println("uuid : " + uuid);  //360939c4-5751-45fa-a3b2-992e817161b6
//		System.out.println("uploadPath : " + uploadPath);  // - /Users/jm/Documents/fileStorage
//		System.out.println("filePath : " + filePath);  // - /Users/jm/Documents/fileStorage/23-0221
		
		File saveFile;
		try {
			saveFile = new File(savePath);  //import java.io.File;
			
			//중복된 파일명이 있을 시 뒤에 괄호로 몇 번째 동일 파일인지 표시하여 업로드
			int cnt = 2;
			while(saveFile.exists()) {
				
				savePath = front + "(" + cnt + ")" + end;
				saveFile = new File(savePath);
				cnt++;
			}
			file.transferTo(saveFile);
			
			//썸네일 경로
			String thumbsPath = savePath.substring(0, savePath.lastIndexOf(".")) + "_thumbs" + end; 
			//썸네일 생성(복사할 파일 위치, 썸네일 생성 위치, 가로, 세로)
			Thumbnailator.createThumbnail(new File(savePath),
										  new File(thumbsPath),
										  150, 150);
			
		} catch (Exception e) {
			System.out.println("Error occur while uploading file : " + e.getMessage());
		}
		
		return "upload/upload_complete";
	}
	
	//multiple 옵션으로 다중 파일 업로드
	@PostMapping("/upload_complete2")
	public String upload_complete2(MultipartHttpServletRequest files) {
		
		//name 값이 testFile 인 태그들
		List<MultipartFile> list = files.getFiles("testFile");
		//반복처리
		for(MultipartFile file : list) {
			
			String originName = file.getOriginalFilename();
			String fileName = originName.substring(originName.lastIndexOf("/") + 1);
			String filePath = makeDir();
			String savePath = filePath + "/" + fileName;
			String front = savePath.substring(0, savePath.lastIndexOf("."));
			String end = savePath.substring(savePath.lastIndexOf("."), savePath.length());
			
			File saveFile;
			try {
				saveFile = new File(savePath);  //import java.io.File;
				
				//중복된 파일명이 있을 시 뒤에 괄호로 몇 번째 동일 파일인지 표시하여 업로드
				int cnt = 2;
				while(saveFile.exists()) {
					
					savePath = front + "(" + cnt + ")" + end;
					saveFile = new File(savePath);
					cnt++;
				}
				file.transferTo(saveFile);
				
				//썸네일 경로
				String thumbsPath = savePath.substring(0, savePath.lastIndexOf(".")) + "_thumbs" + end; 
				//썸네일 생성(복사할 파일 위치, 썸네일 생성 위치, 가로, 세로)
				Thumbnailator.createThumbnail(new File(savePath),
											  new File(thumbsPath),
											  150, 150);
				
			} catch (Exception e) {
				System.out.println("Error occur while uploading file : " + e.getMessage());
			}
		}
		
		return "upload/upload_complete";
	}
	
	//복수태그로 여러 파일 업로드
	@PostMapping("/upload_complete3")
	public String upload_complete3(@RequestParam("testFile") List<MultipartFile> list) {
		
		list = list.stream().filter((x) -> x.isEmpty() == false).collect(Collectors.toList());
		
		for(MultipartFile file : list) {
			
			String originName = file.getOriginalFilename();
			String fileName = originName.substring(originName.lastIndexOf("/") + 1);
			String filePath = makeDir();
			String savePath = filePath + "/" + fileName;
			String front = savePath.substring(0, savePath.lastIndexOf("."));
			String end = savePath.substring(savePath.lastIndexOf("."), savePath.length());
			
			File saveFile;
			try {
				saveFile = new File(savePath);  //import java.io.File;
				
				//중복된 파일명이 있을 시 뒤에 괄호로 몇 번째 동일 파일인지 표시하여 업로드
				int cnt = 2;
				while(saveFile.exists()) {
					
					savePath = front + "(" + cnt + ")" + end;
					saveFile = new File(savePath);
					cnt++;
				}
				file.transferTo(saveFile);
				
				//썸네일 경로
				String thumbsPath = savePath.substring(0, savePath.lastIndexOf(".")) + "_thumbs" + end; 
				//썸네일 생성(복사할 파일 위치, 썸네일 생성 위치, 가로, 세로)
				Thumbnailator.createThumbnail(new File(savePath),
											  new File(thumbsPath),
											  150, 150);
				
			} catch (Exception e) {
				System.out.println("Error occur while uploading file : " + e.getMessage());
			}
		}
		
		return "upload/upload_complete";
	}
	
	//비동기 업로드
	@PostMapping("/upload_complete4")
	@ResponseBody  //return 값이 요청이 들어온 곳으로 넘어간다.
	public String upload_complete4(@RequestParam("testFile") MultipartFile file,
								   @RequestParam("writer") String writer) {
		
		String originName = file.getOriginalFilename();  //IE, Edge는 전체 경로가 들어오므로 파일명만 추출
		String fileName = originName.substring(originName.lastIndexOf("/") + 1);  //서버에서 저장할 파일 이름
		String filePath = makeDir();  //날짜별 업로드 폴더 생성
		String savePath = filePath + "/" + fileName;  //최종 업로드 경로
		String front = savePath.substring(0, savePath.lastIndexOf("."));  //순수 파일명(winter)
		String end = savePath.substring(savePath.lastIndexOf("."), savePath.length());  //. + 확장자(.jpg)
		
		System.out.println("writer : " + writer);
		
		File saveFile;
		try {
			saveFile = new File(savePath);  //import java.io.File;
			
			//중복된 파일명이 있을 시 뒤에 괄호로 몇 번째 동일 파일인지 표시하여 업로드
			int cnt = 2;
			while(saveFile.exists()) {
				
				savePath = front + "(" + cnt + ")" + end;
				saveFile = new File(savePath);
				cnt++;
			}
			file.transferTo(saveFile);
			
		} catch (Exception e) {
			System.out.println("Error occur while uploading file : " + e.getMessage());
		}
		
		return "File upload succeed!";
	}
	
}
