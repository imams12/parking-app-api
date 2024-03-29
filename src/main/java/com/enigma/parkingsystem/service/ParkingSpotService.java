package com.enigma.parkingsystem.service;

import com.enigma.parkingsystem.model.entity.Car;
import com.enigma.parkingsystem.model.entity.ParkingSpot;
import com.enigma.parkingsystem.model.request.CarRequest;
import com.enigma.parkingsystem.model.request.ParkingSpotRequest;
import com.enigma.parkingsystem.model.response.CarResponse;
import com.enigma.parkingsystem.model.response.ParkingSpotResponse;

import java.util.List;

public interface ParkingSpotService {
    ParkingSpotResponse create(ParkingSpotRequest parkingSpotRequest);
    ParkingSpotResponse findBySpotNumber(String spotNumber);
    ParkingSpot getById(String id);
    ParkingSpot findBySpotNumberForTransaction(String spotNumber);
    List<ParkingSpotResponse> findAvailableSpots();
}
