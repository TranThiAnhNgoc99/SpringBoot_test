package com.example.spring01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;

	
	@Autowired
	private JwtEntryPoint jwtEntryPoint;
	
	@Bean
	public JwtFilter authJwtTokenFilter() {
		return new JwtFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.inMemoryAuthentication()
		.withUser("Admin123").password(passwordEncoder().encode("Admin@123")).roles("ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/fonts/**");
		web.ignoring().antMatchers("/v3/api-docs/**");
	    web.ignoring().antMatchers("/swagger.json");
	    web.ignoring().antMatchers("/swagger-ui.html");
	    web.ignoring().antMatchers("/swagger-ui/**");
	    web.ignoring().antMatchers("/swagger-resources/**");
	    web.ignoring().antMatchers("/webjars/**");
	}
	
	 @Override
     protected void configure(HttpSecurity http) throws Exception
     {
         http
             .csrf().disable()
     		.authorizeRequests().antMatchers("/api/login/**", "/login/**", "/api/join/**", "/join/**", "/swagger-ui.html/**", "/swagger-ui/****", "/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/").permitAll()
     		.antMatchers("/api/admin/**").access("hasRole('ADMIN')")
     		.antMatchers("/api/**").authenticated()
     		.and()
     		.exceptionHandling().accessDeniedHandler(new SimpleAccessDeniedHandler())
     		.authenticationEntryPoint(jwtEntryPoint)
     		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

         http.addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
     }
	 
}
