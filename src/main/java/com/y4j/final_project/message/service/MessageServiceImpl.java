package com.y4j.final_project.message.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.MessageVO;

@Service @Primary
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	
	
	//쪽지 발송 메서드
	public int sendMsg(MessageVO vo) {
		return messageMapper.sendMsg(vo);
	}
	
	//받은 쪽지 조회 메서드(id 기준)
	public ArrayList<MessageVO> getReceivedMsg(String receiver) {
		return messageMapper.getReceivedMsg(receiver);
	}
	
	//보낸 쪽지 조회 메서드(id 기준)
	public ArrayList<MessageVO> getSentMsg(String writer) {
		return messageMapper.getSentMsg(writer);
	}
	
}