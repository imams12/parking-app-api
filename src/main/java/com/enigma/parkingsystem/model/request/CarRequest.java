package com.enigma.parkingsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    private String id;
    private String licensePlate;
    private String color;
    private String brand;
    private String customerId;
}
