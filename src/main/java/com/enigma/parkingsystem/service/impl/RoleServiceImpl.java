package com.enigma.parkingsystem.service.impl;

import com.enigma.parkingsystem.constant.ERole;
import com.enigma.parkingsystem.model.entity.Role;
import com.enigma.parkingsystem.repository.RoleRepository;
import com.enigma.parkingsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> optionalRole = repository.findByName(role);

        if(!optionalRole.isEmpty()){
            return optionalRole.get();
        }

        Role currentRole = Role.builder()
                .name(role)
                .build();
        return repository.saveAndFlush(currentRole);
    }
}
