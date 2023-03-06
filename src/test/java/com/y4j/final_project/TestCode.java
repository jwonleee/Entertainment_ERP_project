package com.y4j.final_project;import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.y4j.final_project.admin.service.AdminMapper;
import com.y4j.final_project.authority.service.AuthorityMapper;
import com.y4j.final_project.aws.service.AwsS3Service;
import com.y4j.final_project.command.AuthorityVO;

@SpringBootTest
public class TestCode {
	
	@Autowired
	private AwsS3Service awsS3Service;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	
//	@Test
//	public void findImg() {
//		String imgPath=awsS3Service.getThumbnailPath("NewJeans-test1.jpeg");
//		System.out.println(imgPath);
//	}
	
//	@Test
//	public void adminTestData() {
//		for(int i=1; i<=120; i++) {
//			AdminVO vo = AdminVO.builder().admin_id("test_admin_id"+(6+i))
//										  .admin_pw("testadminpw"+(6+i))
//										  .admin_name("관리자"+(6+i))
//										  .admin_contact("010-1000"+(1006+i))
//										  .admin_address("주소"+(6+i))
//										  .admin_gender(i % 2 == 0 ? "M" : "F")
//										  .admin_email("test_admin_email" + (6+i) + "@y4j.com")
//										  .build();
//			adminMapper.registAdmin(vo);
//		}
//	}
	
//	@Test
//	public void AuthorityTestData() {
//		for(int i=1; i<=120; i++) {
//			AuthorityVO vo = AuthorityVO.builder().authority_mng_admin_no(i+6)
//										  .authority_mng_admin_id("test_admin_id"+(6+i))
//										  .authority_mng_admin_name("관리자"+(6+i))
//										  .authority_mng_admin_apply_type(i % 3 == 0 ? "manager" : i % 3 == 1 ? "audition" : "sale")
//										  .build();
//			if(vo.getAuthority_mng_admin_apply_type().equals("manager")) {
//				if(i % 5 == 0) vo.setEnt_name("Blackpink");
//				if(i % 5 == 1) vo.setEnt_name("NEWJEANS");
//				if(i % 5 == 2) vo.setEnt_name("IVE");
//				if(i % 5 == 3) vo.setEnt_name("CHAESOOBIN");
//				if(i % 5 == 4) vo.setEnt_name("JANGKIYONG");
//			}
//			
//			authorityMapper.applyAuthority(vo);
//		}
//	}
	
}
