package com.eventbooking.service;

import com.eventbooking.service.dto.*;

public interface AuthenticationService {

    UserDTO register(SignUpRequestDTO signUpRequestDTO);

    JwtAuthenticationResponse login(SignInRequestDTO signInRequestDTO);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
