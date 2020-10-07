package com.arcanjo.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcanjo.cursomc.dto.EmailDTO;
import com.arcanjo.cursomc.dto.PasswordDTO;
import com.arcanjo.cursomc.security.JWTUtil;
import com.arcanjo.cursomc.security.UserSS;
import com.arcanjo.cursomc.services.AuthService;
import com.arcanjo.cursomc.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		
		authService.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();

	}
	
	@PostMapping("/change_password")
	public ResponseEntity<Void> forgot(@Valid @RequestBody PasswordDTO objDto, HttpServletResponse response) {
		
		authService.changePassword(objDto.getEmail(), objDto.getPassword(), objDto.getNewPassword());
		String token = jwtUtil.generateToken(UserService.authenticated().getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();

	}
}
