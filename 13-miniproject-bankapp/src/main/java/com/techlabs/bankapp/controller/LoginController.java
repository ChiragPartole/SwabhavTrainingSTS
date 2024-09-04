package com.techlabs.bankapp.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.techlabs.bankapp.dto.JwtAuthReponse;
import com.techlabs.bankapp.dto.LoginDto;
import com.techlabs.bankapp.dto.RegistrationDto;
import com.techlabs.bankapp.entity.User;
import com.techlabs.bankapp.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/bankingapp/api")
public class LoginController {

	@Autowired
	private AuthService authService;
	
	@Autowired
    private DefaultKaptcha defaultKaptcha;


	@GetMapping("/captcha")
	public void getCaptcha(HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		String text = defaultKaptcha.createText();
		BufferedImage image = defaultKaptcha.createImage(text);
		
		request.getSession().setAttribute("captcha", text);
		
		response.setContentType("image/jpeg");
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "jpg", outputStream);
		outputStream.close();
	}
	
	
	public boolean verifyCaptcha(HttpServletRequest request, @RequestParam String captcha) {
		String captchaSession = (String) request.getSession().getAttribute("captcha");
		if (captcha.equals(captchaSession)) {
			return true;
		} else {
			return false;
		}
	}


	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegistrationDto registrationDto){
		return ResponseEntity.ok(authService.register(registrationDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthReponse> login(@RequestBody LoginDto loginDto,HttpServletRequest request){
		if(!verifyCaptcha(request,loginDto.getCaptcha()))
			throw new NullPointerException("captcha invalid");
		String token = authService.login(loginDto);
		JwtAuthReponse jwtAuthResponse = new JwtAuthReponse();
		jwtAuthResponse.setAccessToken(token);
		return ResponseEntity.ok(jwtAuthResponse);
	}
	
}

