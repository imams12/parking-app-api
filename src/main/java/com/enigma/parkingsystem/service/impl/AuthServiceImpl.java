package com.enigma.parkingsystem.service.impl;

import com.enigma.parkingsystem.constant.ERole;
import com.enigma.parkingsystem.model.entity.*;
import com.enigma.parkingsystem.model.request.AuthRequest;
import com.enigma.parkingsystem.model.response.LoginResponse;
import com.enigma.parkingsystem.model.response.RegisterResponse;
import com.enigma.parkingsystem.repository.UserRepository;
import com.enigma.parkingsystem.security.JwtUtil;
import com.enigma.parkingsystem.service.AdminService;
import com.enigma.parkingsystem.service.AuthService;
import com.enigma.parkingsystem.service.CustomerService;
import com.enigma.parkingsystem.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminService adminService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerAdmin(AuthRequest request) {
        try {
            Role role =(request.getAddress() != null) ?  roleService.getOrSave(ERole.ROLE_CUSTOMER) : roleService.getOrSave(ERole.ROLE_ADMIN);

            User user = User.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userRepository.saveAndFlush(user);

            if (role.getName().equals(ERole.ROLE_ADMIN)) {
                Admin admin = Admin.builder()
                        .name(request.getName())
                        .mobilePhone(request.getMobilePhone())
                        .user(user)
                        .build();
                adminService.create(admin);
            }else {
                Customer customer = Customer.builder()
                        .name(request.getName())
                        .address(request.getAddress())
                        .phone(request.getMobilePhone())
                        .user(user)
                        .build();
                customerService.create(customer);
            }

            return RegisterResponse.builder()
                    .username(user.getUsername())
                    .role(user.getRole().getName().toString())
                    .build();

        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername().toLowerCase(),authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
