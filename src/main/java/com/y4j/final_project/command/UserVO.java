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
public class UserVO {

	/*
	 * @NotNull: null값만 허용하지 않는다 - String, wrapper타입의 Integer, Long타입 등에 적용
	 * @NotBlank: null과 공백을 허용하지 않는다 - String에만 적용
	 * @Pattern: 정규표현식에 맞는 문자열을 정의할 수 있음 (값의 유효성을 개발자가 지정할 수 있음) - String에만 적용
	 * @Email: 이메일 형식 검증 (공백은 통과해서 @NotBlank랑 같이 사용하는 것이 좋다)
	 * @Min: 최소값
	 * @Max: 최대값
	 */
	
	private int user_no;
	
	@NotBlank (message = "아이디를 입력하세요")
	@Pattern (regexp = "(?=.*[a-z0-9]).{4,16}", message = "아이디는 영소문자와 숫자로만 입력하세요")
	private String user_id;
	
	@NotBlank(message = "비밀번호를 입력하세요")
	@Pattern (regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 영문 숫자 특수문자를 모두 포함하여 8~16자로 입력하세요")
	private String user_pw;
	
	@NotBlank (message = "이름을 입력하세요")
	@Pattern (regexp = "(?=.*[a-zA-Z가-힣]).{1,16}", message = "이름은 숫자를 입력할 수 없습니다")
	private String user_name;
	
	@NotBlank (message = "생년월일을 입력하세요")
	@Pattern (regexp = "(?=.*[0-9]).{2,}", message = "생년월일을 정확히 입력하세요")
	private String user_birth;
	
	@NotBlank (message = "휴대전화를 입력하세요")
//	@Pattern (regexp = "(?=.*[0-9])", message = "휴대번호를 정확히 입력하세요")
	private String user_contact;
	
	@NotBlank(message="우편번호를 입력하세요")
	private String user_zipcode;
	
	@NotBlank (message = "주소를 입력하세요")
	private String user_address;
	
	@NotBlank(message = "이메일을 입력하세요")
	@Email(message = "이메일 형식으로 입력하세요")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
	private String user_email;
	
	@NotBlank (message = "성별을 선택하세요")
	private String user_gender;
	
	@NotBlank (message = "좋아하는 연예인을 선택하세요")
	private String user_fav_ent;
	
	private LocalDateTime user_regdate;

	
}
