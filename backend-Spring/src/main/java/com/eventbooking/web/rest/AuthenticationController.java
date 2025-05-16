package com.eventbooking.web.rest;

import com.eventbooking.service.AuthenticationService;
import com.eventbooking.service.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        UserDTO userDTO = authenticationService.register(signUpRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequestDTO signInRequestDTO) {
        JwtAuthenticationResponse jwtAuthResponse = authenticationService.login(signInRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(jwtAuthResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        JwtAuthenticationResponse jwtAuthResponse = authenticationService.refreshToken(refreshTokenRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(jwtAuthResponse);
    }
}
