package com.daily.config.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daily.config.authentication.dto.AuthenticationDTO;
import com.daily.config.infra.TokenService;
import com.daily.user.dto.LoginResponseDTO;
import com.daily.user.dto.RegisterDTO;
import com.daily.user.model.User;
import com.daily.user.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	TokenService tokenService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository repository;

	public ResponseEntity login(AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((User) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	public ResponseEntity register(RegisterDTO data) {
		if (this.repository.findByLogin(data.login()) != null) {
			return ResponseEntity.badRequest().build();
		}
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.login(), encryptedPassword, data.role());
		this.repository.save(newUser);
		return ResponseEntity.ok().build();
	}

}
