package com.enigma.parkingsystem.service.impl;

import com.enigma.parkingsystem.model.entity.Car;
import com.enigma.parkingsystem.model.entity.ParkingLot;
import com.enigma.parkingsystem.model.entity.ParkingSpot;
import com.enigma.parkingsystem.model.request.ParkingSpotRequest;
import com.enigma.parkingsystem.model.response.ParkingLotResponse;
import com.enigma.parkingsystem.model.response.ParkingSpotResponse;
import com.enigma.parkingsystem.repository.ParkingLotRepository;
import com.enigma.parkingsystem.repository.ParkingSpotRepository;
import com.enigma.parkingsystem.service.ParkingLotService;
import com.enigma.parkingsystem.service.ParkingSpotService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingLotService parkingLotService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ParkingSpotResponse create(ParkingSpotRequest parkingSpotRequest) {

        ParkingLot parkingLot = parkingLotService.getByIdForSpot(parkingSpotRequest.getParkingLotId());

        ParkingSpot parkingSpot = ParkingSpot.builder()
                .spotNumber(parkingSpotRequest.getSpotNumber())
                .availability(true)
                .parkingLot(parkingLot)
                .build();
        parkingSpotRepository.save(parkingSpot);

        return toParkingSpotResponse(parkingSpot);
    }

    private ParkingSpotResponse toParkingSpotResponse(ParkingSpot parkingSpot) {
        return ParkingSpotResponse.builder()
                .id(parkingSpot.getId())
                .spotNumber(parkingSpot.getSpotNumber())
                .availability(parkingSpot.getAvailability())
                .parkingLotResponse(ParkingLotResponse.builder()
                        .id(parkingSpot.getParkingLot().getId())
                        .areaName(parkingSpot.getParkingLot().getAreaName())
                        .capacity(parkingSpot.getParkingLot().getCapacity())
                        .hourlyRate(parkingSpot.getParkingLot().getHourlyRate())
                        .build())
                .build();
    }

    @Override
    public ParkingSpot getById(String id) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElseThrow();
        return parkingSpot;
    }

    @Override
    public ParkingSpotResponse findBySpotNumber(String spotNumber) {
        ParkingSpot parkingSpot = parkingSpotRepository.findBySpotNumber(spotNumber);

        return toParkingSpotResponse(parkingSpot);
    }

    @Override
    public ParkingSpot findBySpotNumberForTransaction(String spotNumber) {
        ParkingSpot parkingSpot = parkingSpotRepository.findBySpotNumber(spotNumber);

        return parkingSpot;
    }

    @Override
    public List<ParkingSpotResponse> findAvailableSpots() {
        List<ParkingSpot> parkingSpots = parkingSpotRepository.findAvailableSpots();

        return parkingSpots.stream().map(this::toParkingSpotResponse)
                .collect(Collectors.toList());
    }
}
