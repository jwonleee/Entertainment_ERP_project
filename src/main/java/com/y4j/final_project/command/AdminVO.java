package com.y4j.final_project.command;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminVO {

	private int admin_no;  //default : auto increment
	private String admin_type;  //default : none
	
	@NotBlank (message = "아이디를 입력하세요")
	@Pattern (regexp = "(?=.*[a-z0-9]).{4,16}", message = "아이디는 영소문자와 숫자로만 입력하세요")
	private String admin_id;
	
	@NotBlank(message = "비밀번호를 입력하세요")
	@Pattern (regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 영문 숫자 특수문자를 모두 포함하여 8~16자로 입력하세요")
	private String admin_pw;
	
	@NotBlank (message = "이름을 입력하세요")
	@Pattern (regexp = "(?=.*[a-zA-Z가-힣]).{1,16}", message = "이름은 숫자를 입력할 수 없습니다")
	private String admin_name;
	
	@NotBlank (message = "휴대전화를 입력하세요")
	private String admin_contact;
	
	@NotBlank (message = "주소를 입력하세요")
	private String admin_address;
	private String admin_gender;
	
	@NotBlank(message = "이메일을 입력하세요")
	@Email(message = "이메일 형식으로 입력하세요")
	@Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "이메일 형식에 맞지 않습니다.")
	private String admin_email;
	private String ent_name;  //default: none
	private LocalDateTime admin_regdate;  //default : current_timestamp
}