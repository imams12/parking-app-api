package com.enigma.parkingsystem.model.response;

import com.enigma.parkingsystem.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private String id;
    private String licensePlate;
    private String color;
    private String brand;
    private String customerName;
}
