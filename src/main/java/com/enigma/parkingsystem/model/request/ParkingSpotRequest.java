package com.enigma.parkingsystem.model.request;

import com.enigma.parkingsystem.model.entity.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotRequest {
    private String id;
    private String spotNumber;
    private String parkingLotId;
}
