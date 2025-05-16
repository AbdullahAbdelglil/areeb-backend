package com.eventbooking.util;

import com.eventbooking.service.UserService;
import com.eventbooking.service.dto.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    private static UserService userService;

    private SecurityUtil(UserService userService) {
        SecurityUtil.userService = userService;
    }

    public static UserDTO getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String email = authentication.getName();
        return userService.findOne(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
