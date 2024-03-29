package com.enigma.parkingsystem.model.response;

import com.enigma.parkingsystem.model.entity.ParkingLot;
import com.enigma.parkingsystem.model.entity.ParkingSpot;
import com.enigma.parkingsystem.model.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ParkingTransactionResponse {
    private String id;
    private String parkingSpotNumber;
    private CarResponse carResponse;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Optional<Double> totalFee;
}
