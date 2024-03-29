package com.enigma.parkingsystem.service.impl;

import com.enigma.parkingsystem.model.entity.ParkingLot;
import com.enigma.parkingsystem.model.request.ParkingLotRequest;
import com.enigma.parkingsystem.model.response.ParkingLotResponse;
import com.enigma.parkingsystem.repository.ParkingLotRepository;
import com.enigma.parkingsystem.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingLotResponse create(ParkingLotRequest parkingLotRequest) {

        ParkingLot parkingLot = ParkingLot.builder()
                .areaName(parkingLotRequest.getAreaName())
                .capacity(parkingLotRequest.getCapacity())
                .hourlyRate(parkingLotRequest.getHourlyRate())
                .build();
        parkingLotRepository.save(parkingLot);

        return toParkingLotResponse(parkingLot);
    }

    private ParkingLotResponse toParkingLotResponse(ParkingLot parkingLot) {
        return ParkingLotResponse.builder()
                .id(parkingLot.getId())
                .areaName(parkingLot.getAreaName())
                .capacity(parkingLot.getCapacity())
                .hourlyRate(parkingLot.getHourlyRate())
                .build();
    }

    @Override
    public ParkingLotResponse getById(String id) {
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElseThrow(() -> new RuntimeException("Parking Lot not found with id: " + id));
        return toParkingLotResponse(parkingLot);
    }

    @Override
    public ParkingLot getByIdForSpot(String id) {
        return parkingLotRepository.findById(id).orElse(null);
    }

    @Override
    public List<ParkingLotResponse> getAll() {
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        return parkingLots.stream()
                .map(this::toParkingLotResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingLotResponse update(ParkingLotRequest parkingLotRequest) {
        ParkingLot existingParkingLot = parkingLotRepository.findById(parkingLotRequest.getId()).orElseThrow(() -> new RuntimeException("Parking Lot not found with id: " + parkingLotRequest.getId()));
        if (existingParkingLot !=null){
            ParkingLot parkingLot = ParkingLot.builder()
                    .id(parkingLotRequest.getId())
                    .areaName(parkingLotRequest.getAreaName())
                    .capacity(parkingLotRequest.getCapacity())
                    .hourlyRate(parkingLotRequest.getHourlyRate())
                    .build();
            parkingLotRepository.save(parkingLot);

            return toParkingLotResponse(parkingLot);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElse(null);
        if (parkingLot != null){
            parkingLotRepository.delete(parkingLot);
        }
    }

    @Override
    public ParkingLot decreaseCapacity(String id) {
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElseThrow(null);
        if (parkingLot.getCapacity() > 0){
            parkingLot.setCapacity(parkingLot.getCapacity() - 1);
            parkingLotRepository.save(parkingLot);

            return parkingLot;
        }
        return null;
    }

    @Override
    public ParkingLot increaseCapacity(String id) {
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElseThrow(null);
        parkingLot.setCapacity(parkingLot.getCapacity() + 1);
        parkingLotRepository.save(parkingLot);

        return parkingLot;
    }
}
