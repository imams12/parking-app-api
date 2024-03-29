package com.enigma.parkingsystem.repository;

import com.enigma.parkingsystem.constant.ERole;
import com.enigma.parkingsystem.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(ERole role);
}
