package com.world.tbt.config;

import com.world.tbt.security.CustomSuccessHandler;
import com.world.tbt.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private CustomSuccessHandler customSuccessHandler;
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsServiceImpl();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/home/**").permitAll()
			.antMatchers("/quantri").hasAnyAuthority("ROLE_ADMIN","ROLE_AUTHOR","ACCESS_HOMEADMIN")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.successHandler(customSuccessHandler)
			.failureHandler(new AuthenticationFailureHandler() {
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
													AuthenticationException exception) throws IOException, ServletException {
					String email = request.getParameter("email");
					String error = exception.getMessage();
					System.out.println("Authenticaion failure at email:"+email);
					System.out.println("Exception message:"+error);
					String redirectUrl = request.getContextPath() + "/login?error=true";
					response.sendRedirect(redirectUrl);
				}
			})
			//.failureUrl("//login?error=true")
			.permitAll()
			.and()
			.logout().deleteCookies("JSESSIONID","username","userid").invalidateHttpSession(true).logoutSuccessUrl("/").permitAll()
			.and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(30*60);
}

	@Override
	public void configure(WebSecurity web) throws Exception 
	{
		web.ignoring().antMatchers("/global/**");
	}
}
