package com.enigma.parkingsystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotResponse {
    private String id;
    private String areaName;
    private Integer capacity;
    private Double hourlyRate;
}
