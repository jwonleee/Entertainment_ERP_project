package com.y4j.final_project.service.orderservice;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.y4j.final_project.command.OrderHistoryVO;
import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.command.ordercommand.AlbumVO;
import com.y4j.final_project.command.ordercommand.CategoryVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;

@Mapper
public interface OrderMapper {
	//리스트
	public int getOrderTotal(Criteria cri); //전체게시글수 조회
	public ArrayList<Admin_orderVO> getOrderList(Criteria cri);//발주목록
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
	public int additionalRegist(Admin_orderVO vo); //추가발주
	public int updateAlbumStock(Admin_orderVO vo); //앨범재고수정
	public int updateProdStock(Admin_orderVO vo); //상품재고수정
	
	//상품수정, 앨범수정
	public AlbumVO getAlbumDetail(Integer album_no); //앨범수정을 위한 상세조회
	public ProductVO getProductDetail(Integer prod_no); //상품수정을 위한 상세조회
	public int albumModify(AlbumVO vo); //앨범정보수정
	public int productModify(ProductVO vo); //상품정보수정 
	
	//카테고리
	public ArrayList<CategoryVO> getCategory(); //대분류 카테고리
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo); //중, 소분류 카테고리
	public ArrayList<CategoryVO> getMidCategory(); //모든 중분류 카테고리
	public ArrayList<String> getSmallCategory(CategoryVO vo); //특정 중분류에 속한 소분류 카테고리의 디테일 이름
	
	//카테고리 추가
	public int bigCategoryReg(CategoryVO vo); //대분류 카테고리 추가
	public int midSmallCategoryReg(CategoryVO vo); //중, 소분류 카테고리 추가
	
	//top10 가져오기
	public ArrayList<ProductVO> getTopProduct();//상품
	public ArrayList<AlbumVO> getTopAlbum();//앨범
	
	//order_history 가져오기
	public ArrayList<OrderHistoryVO> getOrderHistoryList(Criteria cri);
	public int getOrderHistoryTotal(Criteria cri); //전체게시글수 조회
	
	//order_history state 변경
	public int updateOrderHistoryState(OrderHistoryVO vo);
	
}
