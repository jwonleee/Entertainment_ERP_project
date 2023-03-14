package com.y4j.final_project.config;

import javax.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
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
				.loginProcessingUrl("/userLoginForm") //formLogin 방식이므로 해당 주소를 어디로 처리할지 정해준다
				.usernameParameter("user_id") //유저 아이디에 해당하는 form의 name을 변경
				.passwordParameter("user_pw") 
				.defaultSuccessUrl("/**", true)
				.permitAll()
				.and()
				.logout()
				.and()
				.authorizeRequests()
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() //모든 사용자가 접근할 수 있도록 허용하는 보안 구성 코드
				.antMatchers("/js/**", "/css/**", "/user/user_join", "/admin/admin_join", "/**").permitAll() //URL 패턴에 대한 권한 검사 예외처리할 경로
				.anyRequest().authenticated();
				
//				.authorizeRequests()
//				.antMatchers("/**", "/user/**").permitAll()    // 로그인, 회원가입, 메인 , 상품페이지, 연예인소개 페이지는 인증 x
//				.anyRequest().authenticated(); // 그 외의 페이지는 인증 필요
			

			return http.build();
	}
	
//	public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/static/js/**","/static/css/**", "/");
//    }
	
	
}
