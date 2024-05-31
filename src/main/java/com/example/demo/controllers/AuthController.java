package com.example.demo.controllers;

import com.example.demo.entities.RefreshToken;
import com.example.demo.entities.user;
import com.example.demo.requests.RefreshRequest;
import com.example.demo.requests.UserLoginRequest;
import com.example.demo.requests.UserRequest;
import com.example.demo.services.userService;
import com.example.demo.responses.AuthResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.services.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	private userService userService;
	
	private PasswordEncoder passwordEncoder;

	private RefreshTokenService refreshTokenService;
	
    public AuthController(AuthenticationManager authenticationManager, userService userService,
    		PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }
    
	@PostMapping("/login")
	public AuthResponse login(@RequestBody UserLoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		List<user> user = userService.getOneUserByEmail(loginRequest.getEmail());
		AuthResponse authResponse = new AuthResponse();
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user.get(0)));
		authResponse.setUserId(user.get(0).getId());
		authResponse.setTur(user.get(0).getTur());
		return authResponse;
	}
	public boolean mailMi(@RequestBody UserRequest registerRequest) {
		if(registerRequest.getEmail().endsWith("@gmail.com"))
			return true;
        return false;
    }
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest) {
		AuthResponse authResponse = new AuthResponse();
		List<user> user3 = userService.getOneUserByEmail(registerRequest.getEmail());
		if(!user3.isEmpty()) {
			authResponse.setMessage("Email already in use.");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}else if(mailMi(registerRequest) != true) {
			authResponse.setMessage("this string not email.");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		user user = new user();
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		//user.setPassword(registerRequest.getPassword());
		user.setGender(registerRequest.getGender());
		user.setTelephonenumber(registerRequest.getTelephonenumber());
		user.setDateofbirth(registerRequest.getDateofbirth());
		user.setDateofregistration(new Date());
		user.setName(registerRequest.getName());
		user.setSurname(registerRequest.getSurname());
		user.setTur("client");
		userService.saveOneUser(user);
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		
		authResponse.setMessage("User successfully registered.");
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);		
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
		AuthResponse response = new AuthResponse();
		RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
		if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
				!refreshTokenService.isRefreshExpired(token)) {

			user user = token.getUser();
			String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
			response.setMessage("token successfully refreshed.");
			response.setAccessToken("Bearer " + jwtToken);
			response.setUserId(user.getId());
			response.setTur(user.getTur());
			return new ResponseEntity<>(response, HttpStatus.OK);		
		} else {
			response.setMessage("refresh token is not valid.");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
	}
	

}
