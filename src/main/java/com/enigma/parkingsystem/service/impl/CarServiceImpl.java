package com.enigma.parkingsystem.service.impl;

import com.enigma.parkingsystem.model.entity.Car;
import com.enigma.parkingsystem.model.entity.Customer;
import com.enigma.parkingsystem.model.request.CarRequest;
import com.enigma.parkingsystem.model.response.CarResponse;
import com.enigma.parkingsystem.repository.CarRepository;
import com.enigma.parkingsystem.service.CarService;
import com.enigma.parkingsystem.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CustomerService customerService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public CarResponse create(CarRequest carRequest) {
        Customer existingCustomer = customerService.getByIdForCar(carRequest.getCustomerId());

        Car car = Car.builder()
                .licensePlate(carRequest.getLicensePlate())
                .brand(carRequest.getBrand())
                .color(carRequest.getColor())
                .customer(existingCustomer)
                .build();
        carRepository.save(car);

        return toCarResponse(car);
    }

    private CarResponse toCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .licensePlate
                        (car.getLicensePlate())
                .brand(car.getBrand())
                .color(car.getColor())
                .customerName(car.getCustomer().getName())
                .build();
    }

    @Override
    public CarResponse getById(String id) {
        Car vehicle = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
            return toCarResponse(vehicle);
    }

    @Override
    public List<CarResponse> getAll() {
        List<Car> vehicles = carRepository.findAll();

        return vehicles.stream()
                .map(this::toCarResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CarResponse update(CarRequest carRequest) {
        Car existingCar = carRepository.findById(carRequest.getId()).orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + carRequest.getId()));
        if (existingCar != null){
            Car car = Car.builder()
                    .id(carRequest.getId())
                    .licensePlate(carRequest.getLicensePlate())
                    .brand(carRequest.getBrand())
                    .color(carRequest.getColor())
                    .customer(existingCar.getCustomer())
                    .build();
            carRepository.save(car);

            return toCarResponse(car);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        Car vehicle = carRepository.findById(id).orElse(null);
        if (vehicle != null){
            carRepository.delete(vehicle);
        }
    }

    @Override
    public Car getByIdForTransaction(String id) {
        return carRepository.findById(id).orElse(null);
    }
}
