package com.y4j.final_project.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.y4j.final_project.command.EntertainerScheduleVO;
import com.y4j.final_project.command.EntertainerVO;
import com.y4j.final_project.command.ScheduleVO;
import com.y4j.final_project.util.Criteria;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleMapper scheduleMapper;
	
	//일정등록
	@Override
	public int regist(ScheduleVO vo) {
		System.out.println(2);
		int result = scheduleMapper.regist(vo);
		return result; //성공시 1, 실패시 0
	}
	
	//schedule_no 가져옴
	@Override
	public int registMap(EntertainerScheduleVO vo2) {
		int result = scheduleMapper.registMap(vo2);
		return result;
	}
	
	//그룹 이름
	@Override
	public List<EntertainerVO> getEntType(EntertainerVO vo) {
		List<EntertainerVO> list = scheduleMapper.getEntType(vo);
		return list;
	}
	//배우 이름
	@Override
	public List<EntertainerVO> getEntType2(EntertainerVO vo) {
		List<EntertainerVO> list = scheduleMapper.getEntType2(vo); 
		return list;
	}
	
	//아티스트-스케줄 유무 확인
	@Override
	public ArrayList<ScheduleVO> getSchedule(String artistSelect) {
		return scheduleMapper.getSchedule(artistSelect);
	}
	
	//일정 목록
	@Override
	public ArrayList<ScheduleVO> getList(Criteria cri) {
		return scheduleMapper.getList(cri);
	}
	
	//일반페이지 일정 - axios
	@Override
	public List<Map<String, Object>> axiosGetList(String schedule_start_time, String ent_name) {
		return scheduleMapper.axiosGetList(schedule_start_time, ent_name);
	}
	
	//일정 전체 개수
	@Override
	public int getScheduleTotal(Criteria cri) {
		return scheduleMapper.getScheduleTotal(cri);
	}
	
	//오늘 일정 개수
	@Override
	public int getTodayScheduleCnt(LocalDate now) {
		return scheduleMapper.getTodayScheduleCnt(now);
	}
	
	//일정 상세
	@Override
	public ScheduleVO getDetail(int schedule_no) {
		return scheduleMapper.getDetail(schedule_no);
	}
	
	//일정 수정 화면(모달)
	@Override
	public ScheduleVO getModifyForm(ScheduleVO vo) {
		return scheduleMapper.getModifyForm(vo);
	}
	
	//일정 수정
	@Override
	public int scheduleModify(ScheduleVO vo) {
		return scheduleMapper.scheduleModify(vo);
	}
	
	//일정 삭제
	@Override
	public int scheduleDelete(int schedule_no) {
		return scheduleMapper.scheduleDelete(schedule_no);
	}
	
}
