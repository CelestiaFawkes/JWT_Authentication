package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.LoginUserdto;

import dto.RegisterUserDto;
import entity.User;
import repositories.Userrepository;
import response.LoginResponse;
import service.JwtService;
import service.AuthenticationService;


@RequestMapping("/auth/")
@RestController
public class AuthenticationController {
	
	private JwtService jwtServices;
	
	private AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public ResponseEntity<User>register(@RequestBody RegisterUserDto registeruserDto)
	{
		User registeredUser = authenticationService.signup(registeruserDto);
		return ResponseEntity.ok(registeredUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserdto loginUserDto)
	{
		User authenticatedUser = authenticationService.authenticate(loginUserDto);
		String jwtToken = jwtServices.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtServices.getExpirationTime());

		return ResponseEntity.ok(loginResponse);
	}
	
	
	
}
