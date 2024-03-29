package com.enigma.parkingsystem.repository;

import com.enigma.parkingsystem.model.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM m_parking_spot WHERE spot_number = :spotNumber")
    ParkingSpot findBySpotNumber(@Param("spotNumber") String spotNumber);

    @Query(nativeQuery = true, value = "SELECT * FROM m_parking_spot WHERE availability = true")
    List<ParkingSpot> findAvailableSpots();
}
