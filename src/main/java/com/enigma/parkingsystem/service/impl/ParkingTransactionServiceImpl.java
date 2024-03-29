package com.enigma.parkingsystem.service.impl;

import com.enigma.parkingsystem.model.entity.Car;
import com.enigma.parkingsystem.model.entity.ParkingSpot;
import com.enigma.parkingsystem.model.entity.ParkingTransaction;
import com.enigma.parkingsystem.model.request.ParkingTransactionRequest;
import com.enigma.parkingsystem.model.response.CarResponse;
import com.enigma.parkingsystem.model.response.ParkingSpotResponse;
import com.enigma.parkingsystem.model.response.ParkingTransactionResponse;
import com.enigma.parkingsystem.model.response.StartParkingResponse;
import com.enigma.parkingsystem.repository.ParkingTransactionRepository;
import com.enigma.parkingsystem.service.CarService;
import com.enigma.parkingsystem.service.ParkingLotService;
import com.enigma.parkingsystem.service.ParkingSpotService;
import com.enigma.parkingsystem.service.ParkingTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingTransactionServiceImpl implements ParkingTransactionService {

    private final ParkingTransactionRepository parkingTransactionRepository;
    private final ParkingSpotService parkingSpotService;
    private final ParkingLotService parkingLotService;
    private final CarService carService;

    @Override
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @Transactional(rollbackOn = Exception.class)
    public StartParkingResponse startParking(ParkingTransactionRequest parkingTransactionRequest) {
        ParkingSpot parkingSpot = parkingSpotService.getById(parkingTransactionRequest.getParkingSpotId());
        Car car = carService.getByIdForTransaction(parkingTransactionRequest.getCarId());

        if (parkingSpot.getAvailability() && car != null && parkingSpot.getParkingLot().getCapacity() > 0){
            parkingSpot.setAvailability(false);
            parkingLotService.decreaseCapacity(parkingSpot.getParkingLot().getId());

            ParkingTransaction parkingTransaction = ParkingTransaction.builder()
                    .parkingSpot(parkingSpot)
                    .car(car)
                    .entryTime(LocalDateTime.now())
                    .exitTime(null)
                    .totalFee(null)
                    .build();
            parkingTransactionRepository.save(parkingTransaction);

            return StartParkingResponse.builder()
                    .id(parkingTransaction.getId())
                    .parkingSpotNumber(parkingTransaction.getParkingSpot().getSpotNumber())
                    .carResponse(CarResponse.builder()
                            .id(parkingTransaction.getCar().getId())
                            .licensePlate(parkingTransaction.getCar().getLicensePlate())
                            .brand(parkingTransaction.getCar().getBrand())
                            .color(parkingTransaction.getCar().getColor())
                            .customerName(parkingTransaction.getCar().getCustomer().getName())
                            .build())
                    .entryTime(parkingTransaction.getEntryTime())
                    .build();
        }
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @Transactional(rollbackOn = Exception.class)
    public ParkingTransactionResponse completeParking(String transactionId) {
        ParkingTransaction parkingTransaction = parkingTransactionRepository.findById(transactionId).orElseThrow(null);
        parkingTransaction.setExitTime(LocalDateTime.now());

        Long durationInHours = ChronoUnit.HOURS.between(parkingTransaction.getEntryTime(), parkingTransaction.getExitTime());
        Long minutes = ChronoUnit.MINUTES.between(parkingTransaction.getEntryTime(), parkingTransaction.getExitTime()) % 60;
        if (minutes > 30) {
            durationInHours++;
        }
        parkingTransaction.setTotalFee(durationInHours * parkingTransaction.getParkingSpot().getParkingLot().getHourlyRate());

        parkingLotService.increaseCapacity(parkingTransaction.getParkingSpot().getParkingLot().getId());
        parkingTransaction.getParkingSpot().setAvailability(true);

        ParkingTransaction saveParkingTransaction = ParkingTransaction.builder()
                .id(parkingTransaction.getId())
                .parkingSpot(parkingTransaction.getParkingSpot())
                .car(parkingTransaction.getCar())
                .entryTime(parkingTransaction.getEntryTime())
                .exitTime(parkingTransaction.getExitTime())
                .totalFee(parkingTransaction.getTotalFee())
                .build();
        parkingTransactionRepository.save(saveParkingTransaction);

        return toParkingTransactionResponse(saveParkingTransaction);
    }

    private ParkingTransactionResponse toParkingTransactionResponse(ParkingTransaction saveParkingTransaction) {
        return ParkingTransactionResponse.builder()
                .id(saveParkingTransaction.getId())
                .parkingSpotNumber(saveParkingTransaction.getParkingSpot().getSpotNumber())
                .carResponse(CarResponse.builder()
                        .id(saveParkingTransaction.getCar().getId())
                        .licensePlate(saveParkingTransaction.getCar().getLicensePlate())
                        .brand(saveParkingTransaction.getCar().getBrand())
                        .color(saveParkingTransaction.getCar().getColor())
                        .customerName(saveParkingTransaction.getCar().getCustomer().getName())
                        .build())
                .entryTime(saveParkingTransaction.getEntryTime())
                .exitTime(saveParkingTransaction.getExitTime())
                .totalFee(Optional.ofNullable(saveParkingTransaction.getTotalFee()))
                .build();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ParkingTransactionResponse> getAll() {
        List<ParkingTransaction> parkingTransactions = parkingTransactionRepository.findAll();
        return parkingTransactions.stream().map(this::toParkingTransactionResponse)
                .collect(Collectors.toList());
    }
}
