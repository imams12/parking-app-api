package com.enigma.parkingsystem.controller;

import com.enigma.parkingsystem.constant.AppPath;
import com.enigma.parkingsystem.model.request.AuthRequest;
import com.enigma.parkingsystem.model.response.CommonResponse;
import com.enigma.parkingsystem.model.response.LoginResponse;
import com.enigma.parkingsystem.model.response.RegisterResponse;
import com.enigma.parkingsystem.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppPath.AUTH)
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping(AppPath.REGISTER)
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest){

        RegisterResponse registerResponse = authService.registerAdmin(authRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully register")
                        .data(registerResponse)
                        .build());
    }

    @PostMapping(AppPath.LOGIN)
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        LoginResponse loginResponse = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("Success Login")
                .statusCode(HttpStatus.OK.value())
                .data(loginResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
