package com.y4j.final_project.service.orderservice;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.y4j.final_project.command.ordercommand.Admin_orderVO;
import com.y4j.final_project.util.Criteria;

@Mapper
public interface OrderMapper {
	//Test
	int testRegist(Admin_orderVO admin_orderVO);
	 
	public int getOrderTotal(@Param("user_id")String user_id, @Param("cri")Criteria cri); //전체게시글수 조회
	public ArrayList<Admin_orderVO> getOrderList(@Param("user_id")String user_id, @Param("cri")Criteria cri);//발주목록
}
