package com.enigma.parkingsystem.model.response;

import com.enigma.parkingsystem.model.entity.Car;
import com.enigma.parkingsystem.model.entity.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotResponse {
    private String id;
    private String spotNumber;
    private Boolean availability;
    private CarResponse carResponse;
    private ParkingLotResponse parkingLotResponse;
}
