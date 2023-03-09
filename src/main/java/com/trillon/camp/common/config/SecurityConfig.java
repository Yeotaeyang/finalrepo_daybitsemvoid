package com.trillon.camp.common.config;

import javax.sql.DataSource;

/*import org.springframework.boot.autoconfigure.security.servlet.PathRequest;*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security의 기본 설정 대신 사용자가 커스터마이징한 설정을 시큐리티에 적용
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final DataSource dataSource;
	/* private final UserDetailsService userDetailsService; */
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
<<<<<<< HEAD
				http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/sample/admin").permitAll()
		.antMatchers(HttpMethod.GET, "/index").permitAll()
		.antMatchers(HttpMethod.GET, "/elements").permitAll()
		.antMatchers(HttpMethod.GET, "/sample/admin").permitAll()
		.antMatchers(HttpMethod.POST,"/member/signup").permitAll()
		.antMatchers(HttpMethod.POST, "/mail").permitAll()
		.antMatchers(HttpMethod.GET,"/board/list", "/board/detail", "/board/download").permitAll()
		.antMatchers(HttpMethod.GET, "/sample/member").hasAuthority("ROLE_MEMBER")
				.antMatchers(HttpMethod.GET,"/campingHome/**").permitAll()
						.antMatchers(HttpMethod.GET,"/members/**").permitAll()
						.anyRequest().authenticated();
=======
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,  "/").permitAll()
		.antMatchers(HttpMethod.GET,  "/sample/admin").permitAll()
		.antMatchers(HttpMethod.GET,  "/index").permitAll()
		.antMatchers(HttpMethod.GET,  "/elements").permitAll()
		.antMatchers(HttpMethod.GET,  "/sample/admin").permitAll()
		.antMatchers(HttpMethod.POST, "/member/signup").permitAll()
		.antMatchers(HttpMethod.POST, "/mail").permitAll()
		.antMatchers(HttpMethod.GET, "/comewithme/comeWithMeList", "/comewithme/comeWithMeSelect", "/comewithme/comeWithMeBoard").permitAll()
		.antMatchers(HttpMethod.GET,  "/members/login").permitAll()
		.antMatchers(HttpMethod.GET,  "/board/list", "/board/detail", "/board/download").permitAll()
		.antMatchers(HttpMethod.GET,  "/sample/member").hasAuthority("ROLE_MEMBER")
		.anyRequest().authenticated();
>>>>>>> 8a14df44f584564bd5eff3e980697548840257ac
		
		
	  http.formLogin() .loginProcessingUrl("/members/login_logic")
	  .loginPage("/members/login") .usernameParameter("userId")
	  //.successHandler(authSuccessHandler) //.failureHandler(authFailureHandler)
	  // /members/login-> /index로 고쳤음 나중에 다시 로그인으로 해야함
	  .permitAll();
		 
		http.logout()
			.logoutUrl("/members/logout")
			.logoutSuccessUrl("/members/login");
		
		/*
		 * http.rememberMe() .userDetailsService(userDetailsService)
		 * .tokenRepository(tokenRepository());
		 */
		
		// csrf : post요청일 때 수행해야 하는 csrf 토큰 검증을 끔
		//http.csrf().disable();
		http.csrf().ignoringAntMatchers("/mail");
	}
	
	
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.GET,"/resources/**");
	}
	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}