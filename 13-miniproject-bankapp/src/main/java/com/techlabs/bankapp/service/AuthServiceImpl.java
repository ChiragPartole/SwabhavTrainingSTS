package com.techlabs.bankapp.service;

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

import com.techlabs.bankapp.dto.LoginDto;
import com.techlabs.bankapp.dto.RegistrationDto;
import com.techlabs.bankapp.entity.Role;
import com.techlabs.bankapp.entity.User;
import com.techlabs.bankapp.exception.UserApiException;
import com.techlabs.bankapp.repository.RoleRepository;
import com.techlabs.bankapp.repository.UserRepository;
import com.techlabs.bankapp.security.JwtTokenProvider;

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
		if(userRepo.existsByUserName(registrationDto.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST,"User already exists");
		
		User user = new User();
		user.setUserName(registrationDto.getUsername());
		user.setUserPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		List<Role> roles = new ArrayList<>();
		
		Role userRole = roleRepo.findByRolename(registrationDto.getRole()).get();
		roles.add(userRole);
		user.setRoles(roles);
		
		return userRepo.save(user);
	}

	@Override
	public String login(LoginDto loginDto) {

		try {
			

			System.out.println(loginDto.getUserName());
			System.out.println(loginDto.getUserPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getUserPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.generateToken(authentication);
		
		return token;
		}catch(BadCredentialsException e) {
			throw new UserApiException(HttpStatus.NOT_FOUND,"Username or password is incorrect");
		}
	}

}
