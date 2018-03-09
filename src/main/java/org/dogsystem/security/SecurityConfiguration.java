package org.dogsystem.security;

import org.dogsystem.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

	public static final String AUTH_USER = "ROLE_USER";

	public static final String AUTH_ADMIN = "ROLE_ADMIN";

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private HeaderHandler headerHandler;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		
		// Global Authority to OPTIONS (permit all).
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		
		// Public (permit all).
		.antMatchers(ServicePath.PUBLIC_ROOT_PATH + ServicePath.ALL).permitAll()

		// User Authorities.
		.antMatchers(HttpMethod.GET, ServicePath.USER_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.POST, ServicePath.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
		.antMatchers(HttpMethod.PUT, ServicePath.USER_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.DELETE, ServicePath.USER_PATH).hasAnyAuthority(AUTH_ADMIN)
		
		// Pets Authorities.
		.antMatchers(HttpMethod.GET, ServicePath.PET_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.POST, ServicePath.PET_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.PUT, ServicePath.PET_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.DELETE, ServicePath.PET_PATH).hasAnyAuthority(AUTH_ADMIN)
		
		// Agenda Authorities.
		.antMatchers(HttpMethod.GET, ServicePath.AGENDA_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.POST, ServicePath.AGENDA_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.PUT, ServicePath.AGENDA_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.DELETE, ServicePath.AGENDA_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		
		// Breed Authorities.
		.antMatchers(HttpMethod.GET, ServicePath.BREED_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.POST, ServicePath.BREED_PATH).hasAnyAuthority(AUTH_ADMIN)
		.antMatchers(HttpMethod.PUT, ServicePath.BREED_PATH).hasAnyAuthority(AUTH_ADMIN)
		.antMatchers(HttpMethod.DELETE, ServicePath.BREED_PATH).hasAnyAuthority(AUTH_ADMIN)
		
		// Services Authorities.
		.antMatchers(HttpMethod.GET, ServicePath.SERVICES_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.antMatchers(HttpMethod.POST, ServicePath.SERVICES_PATH).hasAnyAuthority(AUTH_ADMIN)
		.antMatchers(HttpMethod.PUT, ServicePath.SERVICES_PATH).hasAnyAuthority(AUTH_ADMIN)
		.antMatchers(HttpMethod.DELETE, ServicePath.SERVICES_PATH).hasAnyAuthority(AUTH_ADMIN)
		
		// Permission Authorities.
		.antMatchers(HttpMethod.GET, ServicePath.PERMISSION_PATH).hasAnyAuthority(AUTH_ADMIN, AUTH_USER)
		.anyRequest().fullyAuthenticated().and()
		
		// Logout configuration.
		.logout().logoutRequestMatcher(new AntPathRequestMatcher(ServicePath.LOGOUT_PATH)).logoutSuccessHandler(headerHandler).and()
		
		.csrf().disable()
		.addFilterAfter(headerHandler, ChannelProcessingFilter.class);
	}

}