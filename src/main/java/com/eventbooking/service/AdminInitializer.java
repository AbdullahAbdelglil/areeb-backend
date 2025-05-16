package com.eventbooking.service;

import com.eventbooking.domain.enums.Role;
import com.eventbooking.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(AdminInitializer.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserService userService,
                            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        Optional<UserDTO> adminAccount = userService.getByRole(Role.ADMIN);

        if(adminAccount.isEmpty()) {
            UserDTO admin = new UserDTO();
            admin.setFirstName("Abdullah");
            admin.setLastName("Abduljalil");
            admin.setEmail("abdullah@gmail.com");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(Role.ADMIN);

            userService.save(admin);

            log.debug("Admin account created on setup");
        }
    }
}
