package com.y4j.final_project.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//sql문에 페이지번호, 데이터개수 전달해줄 클래스
public class Criteria {

	private int pageNum;  //페이지 번호
	private int amount;  //페이지 당 출력할 데이터의 수
	
	private String searchName;  //상품 이름 검색
	private String searchContent;  //상품 내용 검색
	private String searchValue = "";  //상품 제외 요소 검색값
	
	public Criteria() {
		this.pageNum = 1;
		this.amount = 10;
	}
	
	//페이지의 첫 번째 게시물 번호 계산
	public int getPageStart() {
		return (pageNum - 1) * amount;
	}

}
