package com.eventbooking.service;

import com.eventbooking.domain.enums.Role;
import com.eventbooking.service.dto.UserDTO;
import com.eventbooking.web.errors.BadRequestAlertException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService {

    UserDTO save(UserDTO userDTO);

    Optional<UserDTO> findOne(String email);

    boolean existsByEmail(String email);

    Optional<UserDTO> getByRole(Role role);

    UserDetailsService userDetailsService();

}
