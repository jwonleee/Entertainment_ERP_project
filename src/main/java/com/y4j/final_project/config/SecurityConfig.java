package com.y4j.final_project.config;


import javax.servlet.DispatcherType;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final AuthenticationSuccessHandler authenticationSuccessHandler = null;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

				http.cors().disable()			//cors 방지
				.csrf().disable()			//csrf 방지
				.headers().frameOptions().disable()
				
				.and()
				.formLogin()
				.loginPage("/user/user_login") //커스텀 페이지로 로그인 페이지를 변경
				.usernameParameter("user_id") //유저 아이디에 해당하는 form의 name을 변경
				.passwordParameter("user_pw") 
				.loginProcessingUrl("/userLoginForm") //formLogin 방식이므로 해당 주소를 어디로 처리할지 정해준다
				.successHandler(authenticationSuccessHandler) // 로그인 성공 시 작동 핸들러
				.defaultSuccessUrl("/**", true)
				.permitAll()
				
				.and()
				 .logout()
				 .logoutUrl("/user_logout")
	             .logoutSuccessUrl("/") // 로그아웃 성공시 메인화면으로 리다이렉트
	             .invalidateHttpSession(true) // 로그아웃 이후 세션 전체 삭제 여부
	             .deleteCookies("JSESSIONID") //로그아웃 이후 쿠키 삭제
	             
				.and()
				.authorizeRequests()
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() //모든 사용자가 접근할 수 있도록 허용하는 보안 구성 코드
				.antMatchers("/js/**", "/css/**", "/user/user_login", "/user/user_join", "/admin/admin_join", "/audition//audition_notice", "audition/audApplyForm", "product/**", "/**").permitAll() //URL 패턴에 대한 권한 검사 예외처리할 경로
				.anyRequest().authenticated();
				
			return http.build();
	}
	
	 @Bean
	 public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	 
		
	
	
}
