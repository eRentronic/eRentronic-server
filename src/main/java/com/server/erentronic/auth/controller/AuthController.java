package com.server.erentronic.auth.controller;

import com.server.erentronic.auth.dto.LoginRequest;
import com.server.erentronic.auth.service.AuthService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public void login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
		String jwtAccessToken = authService.login(loginRequest);
		response.setHeader("Access-Token", "bearer " + jwtAccessToken);
	}
}
