package com.enigma.parkingsystem.repository;

import com.enigma.parkingsystem.model.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
}
