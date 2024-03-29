package com.enigma.parkingsystem.controller;

import com.enigma.parkingsystem.constant.AppPath;
import com.enigma.parkingsystem.model.request.ParkingTransactionRequest;
import com.enigma.parkingsystem.model.response.CommonResponse;
import com.enigma.parkingsystem.model.response.ParkingTransactionResponse;
import com.enigma.parkingsystem.model.response.StartParkingResponse;
import com.enigma.parkingsystem.service.ParkingTransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppPath.TRANSACTION)
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ParkingTransactionController {

    private final ParkingTransactionService parkingTransactionService;

    @PostMapping
    public ResponseEntity<?> start(@RequestBody ParkingTransactionRequest parkingTransactionRequest){

        StartParkingResponse startParkingResponse = parkingTransactionService.startParking(parkingTransactionRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created new transaction")
                        .data(startParkingResponse)
                        .build());
    }

    @PutMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> complete(@PathVariable String id){

        ParkingTransactionResponse parkingTransactionResponse = parkingTransactionService.completeParking(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully completed transaction id "+ id)
                        .data(parkingTransactionResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(){

        List<ParkingTransactionResponse> parkingTransactionResponses = parkingTransactionService.getAll();

        return  ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all transactions")
                        .data(parkingTransactionResponses)
                        .build());
    }



}
