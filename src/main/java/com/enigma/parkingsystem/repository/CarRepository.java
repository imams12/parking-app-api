package com.enigma.parkingsystem.repository;

import com.enigma.parkingsystem.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {
}
