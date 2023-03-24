package com.y4j.final_project.service.orderservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.command.ordercommand.AlbumVO;
import com.y4j.final_project.command.ordercommand.CategoryVO;
import com.y4j.final_project.command.ordercommand.ProductVO;
import com.y4j.final_project.util.Criteria;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;



	@Override
	public int getOrderTotal(String user_id, Criteria cri) {
		return orderMapper.getOrderTotal(user_id, cri);
	}

	@Override
	public ArrayList<Admin_orderVO> getOrderList(String user_id,Criteria cri) {
		return orderMapper.getOrderList(user_id,cri);
	}

	@Override
	public Admin_orderVO getDetail(String admin_order_no) {
		return orderMapper.getDetail(admin_order_no);
	}

	@Override
	public int albumRegist(AlbumVO vo) {
		return orderMapper.albumRegist(vo);
	}

	@Override
	public int productRegist(ProductVO vo) {
		return orderMapper.productRegist(vo);
	}

	@Override
	public int adminAlbumRegist(Admin_orderVO admvo) {
		return orderMapper.adminAlbumRegist(admvo);
	}

	@Override
	public int adminProductmRegist(Admin_orderVO admvo) {
		return orderMapper.adminProductmRegist(admvo);
	}


	@Override
	public ArrayList<CategoryVO> getCategory() {
		return orderMapper.getCategory();
	}

	@Override
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo) {
		return orderMapper.getCategoryChild(vo);
	}

	@Override
	public ArrayList<ProductVO> getProductList(Criteria cri) {
		return orderMapper.getProductList(cri);
	}

	@Override
	public ArrayList<AlbumVO> getAlbumList(Criteria cri) {
		return orderMapper.getAlbumList(cri);
	}

	@Override
	public AlbumVO getAlbum(String admin_order_album_no) {
		return orderMapper.getAlbum(admin_order_album_no);
	}

	@Override
	public ProductVO getProduct(String admin_order_prod_no) {
		return orderMapper.getProduct(admin_order_prod_no);
	}

	@Override
	public AlbumVO getAlbumDetail(Integer album_no) {
		return orderMapper.getAlbumDetail(album_no);
	}

	@Override
	public ProductVO getProductDetail(Integer prod_no) {
		return orderMapper.getProductDetail(prod_no);
	}

	@Override
	public int albumModify(AlbumVO vo) {
		return orderMapper.albumModify(vo);
	}

	@Override
	public int productModify(ProductVO vo) {
		return orderMapper.productModify(vo);
	}

	@Override
	public int additionalRegist(Admin_orderVO vo) {
		return orderMapper.additionalRegist(vo);
	}

	@Override
	public int updateAlbumStock(Admin_orderVO vo) {
		return orderMapper.updateAlbumStock(vo);
	}

	@Override
	public int updateProdStock(Admin_orderVO vo) {
		return orderMapper.updateProdStock(vo);
	}

	@Override
	public int bigCategoryReg(CategoryVO vo) {
		return orderMapper.bigCategoryReg(vo);
	}

	@Override
	public int midSmallCategoryReg(CategoryVO vo) {
		return orderMapper.midSmallCategoryReg(vo);
	}

	@Override
	public ArrayList<CategoryVO> getMidCategory() {
		return orderMapper.getMidCategory();
	}

	@Override
	public ArrayList<String> getSmallCategory(CategoryVO vo) {
		return orderMapper.getSmallCategory(vo);
	}

	@Override
	public ArrayList<Map<String, String>> getTopProduct() {
		ArrayList<Map<String, String>> list = new ArrayList<>();

		ArrayList<ProductVO> parr = orderMapper.getTopProduct();
		for(int i = 0; i < parr.size(); i++) {
			ProductVO data = parr.get(i);
			String art = data.getProd_artist();
			String name = data.getProd_name();
			String volume = Integer.toString(data.getProd_sales_volume());

			Map<String, String> map = new HashMap<>();
			map.put("art", art);
			map.put("name", name);
			map.put("volume", volume);

			list.add(map);
		}	

		return list;
	}


	@Override
	public ArrayList<Map<String, String>> getTopAlbum() {
		ArrayList<Map<String, String>> list = new ArrayList<>();

		ArrayList<AlbumVO> aarr = orderMapper.getTopAlbum();
		for(int i = 0; i < aarr.size(); i++) {
			AlbumVO data = aarr.get(i);
			String art = data.getAlbum_artist();
			String name = data.getAlbum_title();
			String volume = Integer.toString(data.getAlbum_sales_volume());

			Map<String, String> map = new HashMap<>();
			map.put("art", art);
			map.put("name", name);
			map.put("volume", volume);

			list.add(map);
		}	

		return list;
	}









}
