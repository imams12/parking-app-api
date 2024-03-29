package com.enigma.parkingsystem.service.impl;

import com.enigma.parkingsystem.model.entity.Admin;
import com.enigma.parkingsystem.repository.AdminRepository;
import com.enigma.parkingsystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }
}
