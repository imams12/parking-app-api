package com.enigma.parkingsystem.repository;

import com.enigma.parkingsystem.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
