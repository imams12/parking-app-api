package com.enigma.parkingsystem.model.response;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class StartParkingResponse {
    private String id;
    private String parkingSpotNumber;
    private CarResponse carResponse;
    private LocalDateTime entryTime;
}
