package com.enigma.parkingsystem.service;

import com.enigma.parkingsystem.model.entity.Car;
import com.enigma.parkingsystem.model.request.CarRequest;
import com.enigma.parkingsystem.model.response.CarResponse;

import java.util.List;

public interface CarService {
    CarResponse create(CarRequest vehicleRequest);
    CarResponse getById(String id);
    Car getByIdForTransaction(String id);
    List<CarResponse> getAll();
    CarResponse update(CarRequest vehicleRequest);
    void delete(String id);
}
