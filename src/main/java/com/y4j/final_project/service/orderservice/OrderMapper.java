package com.y4j.final_project.service.orderservice;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.command.ordercommand.AlbumVO;
import com.y4j.final_project.command.ordercommand.CategoryVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;

@Mapper
public interface OrderMapper {
	//리스트
	public int getOrderTotal(@Param("user_id")String user_id, @Param("cri")Criteria cri); //전체게시글수 조회
	public ArrayList<Admin_orderVO> getOrderList(@Param("user_id")String user_id, @Param("cri")Criteria cri);//발주목록
	public ArrayList<ProductVO> getProductList(Criteria cri); //상품목록
	public ArrayList<AlbumVO> getAlbumList(Criteria cri); //앨범목록
	
	
	//신규등록
	public int albumRegist(AlbumVO vo);	//앨범등록
	public int productRegist(ProductVO vo);	//상품등록
	public int adminAlbumRegist(Admin_orderVO admvo); //앨범일 때 관리자 판매 등록
	public int adminProductmRegist(Admin_orderVO admvo); //상품일 때 관리자 판매 등록
	
	//상세조회,추가발주
	public Admin_orderVO getDetail(String admin_order_no);//상세조회
	public AlbumVO getAlbum(String admin_order_album_no); //앨범 상세조회
	public ProductVO getProduct(String admin_order_prod_no); //상품 상세조회
	
	//카테고리
	public ArrayList<CategoryVO> getCategory(); //대분류 카테고리
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo); //중, 소분류 카테고리
	
}
