package com.enigma.parkingsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ParkingTransactionRequest {
    private String id;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private String parkingSpotId;
    private String carId;
}
