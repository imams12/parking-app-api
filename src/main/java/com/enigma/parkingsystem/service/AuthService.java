package com.enigma.parkingsystem.service;

import com.enigma.parkingsystem.model.request.AuthRequest;
import com.enigma.parkingsystem.model.response.LoginResponse;
import com.enigma.parkingsystem.model.response.RegisterResponse;

public interface AuthService {

    RegisterResponse registerAdmin(AuthRequest authRequest);
    LoginResponse login(AuthRequest authRequest);
}
