package com.eventbooking.service;

import com.eventbooking.service.dto.UserDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JWTService {
    String generateToken(UserDetails userDetails, UserDTO account);

    String generateToken(UserDTO userDTO);

    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(HashMap<String, Object> map, UserDTO user);
}
