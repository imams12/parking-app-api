package com.enigma.parkingsystem.service;

import com.enigma.parkingsystem.model.entity.ParkingLot;
import com.enigma.parkingsystem.model.request.ParkingLotRequest;
import com.enigma.parkingsystem.model.response.ParkingLotResponse;

import java.util.List;

public interface ParkingLotService {
    ParkingLotResponse create(ParkingLotRequest parkingLotRequest);

    ParkingLotResponse getById(String id);

    ParkingLot getByIdForSpot(String id);

    ParkingLot decreaseCapacity(String id);
    ParkingLot increaseCapacity(String id);
    List<ParkingLotResponse> getAll();
    ParkingLotResponse update(ParkingLotRequest parkingLotRequest);
    void delete(String id);
}
