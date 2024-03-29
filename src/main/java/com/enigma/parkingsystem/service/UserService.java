package com.enigma.parkingsystem.service;

import com.enigma.parkingsystem.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    AppUser loadUserByUserId(String id);
}
