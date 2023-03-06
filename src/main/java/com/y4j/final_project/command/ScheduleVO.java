package com.y4j.final_project.command;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleVO {
	
	/* 서버에서 유효성 검사
	 * @NotNull - null값만 허용하지 않음, 공백으로 주면 에러 안남 (wrapper의Integer, Long, String 등)
	 * @NotBlank - null값과 공백 허용하지 않음 (String에만 적용)
	 * @NotEmpty - null값을 허용하지 않음 (Array, list 적용)
	 * @Pattern - 정규표현식에 맞는 문자열을 정의할 수 있음 (String에만 적용)
	 * 
	 * @Email - 이메일 형식 검증 (공백은 통과)
	 * @Min - 최소값
	 * @Max - 최대값
	 */

	private int schedule_no;
	private String schedule_start_time;
	private String schedule_end_time;
	private String schedule_type;
	private String schedule_ent_name;
	
	//아티스트 조인 결과?
	private String ent_type;
	private String ent_name;
	private String ent_group_name;
	
	private String schedule_location;
	private String schedule_content;
	
}
