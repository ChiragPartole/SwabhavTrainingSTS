package com.techlabs.jwtsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.jwtsecurity.dto.LoginDto;
import com.techlabs.jwtsecurity.dto.RegistrationDto;
import com.techlabs.jwtsecurity.entity.Role;
import com.techlabs.jwtsecurity.entity.User;
import com.techlabs.jwtsecurity.exception.UserApiException;
import com.techlabs.jwtsecurity.repository.RoleRepository;
import com.techlabs.jwtsecurity.repository.UserRepository;
import com.techlabs.jwtsecurity.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Override
	public User register(RegistrationDto registrationDto) {
		if(userRepo.existsByUsername(registrationDto.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST,"User already exists");
		
		User user = new User();
		user.setUsername(registrationDto.getUsername());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		List<Role> roles = new ArrayList<>();
		
		Role userRole = roleRepo.findByRolename(registrationDto.getRole()).get();
		roles.add(userRole);
		user.setRoles(roles);
		
		return userRepo.save(user);
	}

	@Override
	public String login(LoginDto loginDto) {

		try {
			

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.generateToken(authentication);
		
		return token;
		}catch(BadCredentialsException e) {
			throw new UserApiException(HttpStatus.NOT_FOUND,"Username or password is incorrect");
		}
	}

}
