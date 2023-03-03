package com.y4j.final_project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.UsersVO;


@Service @Primary
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	public ArrayList<UsersVO> getList() {
		
		return userMapper.getList();
	}
	
}
