package com.enigma.parkingsystem.service;

import com.enigma.parkingsystem.model.entity.Car;
import com.enigma.parkingsystem.model.entity.ParkingTransaction;
import com.enigma.parkingsystem.model.request.ParkingTransactionRequest;
import com.enigma.parkingsystem.model.response.ParkingTransactionResponse;
import com.enigma.parkingsystem.model.response.StartParkingResponse;

import java.time.LocalTime;
import java.util.List;

public interface ParkingTransactionService {

    StartParkingResponse startParking(ParkingTransactionRequest parkingTransactionRequest);

    ParkingTransactionResponse completeParking(String transactionId);

    List<ParkingTransactionResponse> getAll();
}
