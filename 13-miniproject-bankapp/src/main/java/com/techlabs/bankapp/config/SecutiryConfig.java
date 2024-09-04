package com.techlabs.bankapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.techlabs.bankapp.security.JwtAuthenticationEntryPoint;
import com.techlabs.bankapp.security.JwtAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableMethodSecurity
@Configuration
public class SecutiryConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
	    http.csrf(csrf -> csrf.disable()).cors(withDefaults());
	    http.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));
	    
	    http.authorizeHttpRequests(request -> request.requestMatchers("/bankingapp/api/register").permitAll());
	    http.authorizeHttpRequests(request -> request.requestMatchers("/bankingapp/api/login").permitAll());
	    http.authorizeHttpRequests(request -> request.requestMatchers("/bankingapp/api/captcha").permitAll());

	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/bankingapp/**").permitAll());
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/bankingapp/**").permitAll());
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.PUT, "/bankingapp/**").permitAll());
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.DELETE, "/bankingapp/**").permitAll());
	    http.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));
	    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
	    return http.build();
	  }
}
