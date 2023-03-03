package com.y4j.final_project.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.y4j.final_project.command.UsersVO;


@Mapper
public interface UserMapper {

	//유저 정보 조회 메서드
	public ArrayList<UsersVO> getList();
}
