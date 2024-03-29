package com.enigma.parkingsystem.repository;

import com.enigma.parkingsystem.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
