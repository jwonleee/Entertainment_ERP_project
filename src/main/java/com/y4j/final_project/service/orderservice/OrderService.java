package com.y4j.final_project.service.orderservice;

import java.util.ArrayList;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.command.ordercommand.AlbumVO;
import com.y4j.final_project.command.ordercommand.CategoryVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;

public interface OrderService {
	//리스트
	public int getOrderTotal(String user_id, Criteria cri); //전체게시글수 조회
	public ArrayList<Admin_orderVO> getOrderList(String user_id, Criteria cri); //발주목록
	
	//신규등록
	public int albumRegist(AlbumVO vo);	//앨범등록
	public int productRegist(ProductVO vo);	//상품등록
	public int adminAlbumRegist(Admin_orderVO admvo); //앨범일 때 관리자 판매 등록
	public int adminProductmRegist(Admin_orderVO admvo); //상품일 때 관리자 판매 등록
	
	//상세조회,추가발주
	public Admin_orderVO getDetail(String admin_order_no); //상세조회
	//public int updateOrder();//추가발주-업데이트
	
	
	//카테고리
	public ArrayList<CategoryVO> getCategory(); //대분류 카테고리
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo); //중, 소분류 카테고리
	
}
