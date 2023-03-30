package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.message.MessageResponse;
import com.project.BookingCar.domain.dto.security.JwtResponse;
import com.project.BookingCar.domain.dto.security.LoginRequest;
import com.project.BookingCar.domain.dto.security.SignupRequest;
import com.project.BookingCar.domain.model.Driver;
import com.project.BookingCar.domain.model.Role;
import com.project.BookingCar.domain.model.User;
import com.project.BookingCar.repository.DriverRepository;
import com.project.BookingCar.repository.RoleRepository;
import com.project.BookingCar.repository.UserRepository;
import com.project.BookingCar.security.jwt.JwtUtils;
import com.project.BookingCar.security.service.UserDetailsImpl;
import com.project.BookingCar.service.AuthService;
import com.project.BookingCar.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.project.BookingCar.domain.enums.RoleEnum.*;
import static com.project.BookingCar.domain.enums.RoleEnum.ROLE_USER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl extends BaseService implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final DriverRepository driverRepository;


    private final UserRepository userRepository;


    private final RoleRepository roleRepository;


    private final PasswordEncoder encoder;


    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public MessageResponse signUp(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        User user = new User(
                signUpRequest.getUsername()
                , signUpRequest.getEmail()
                , encoder.encode(signUpRequest.getPassword())
        );

        Set<String> asignRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        // Nếu không truyền thì set role mặc định là ROLE_USER
        if (asignRoles == null) {
            Role userRole = roleRepository.findByName(ROLE_USER.getValue())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            asignRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ROLE_ADMIN.getValue())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ROLE_MODERATOR.getValue())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ROLE_USER.getValue())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        Driver driver = new Driver();
        driver.setCreatedAt(LocalDateTime.now());
        driver.setCreateUser(user.getUsername());
        driver.setUsername(user.getUsername());
        driver.setEmail(user.getEmail());
        driver.setUser(user);
        driverRepository.save(driver);
        userRepository.save(user);

        return MessageResponse.builder().name("User registered successfully!").build();
    }
}
