package com.enigma.parkingsystem.controller;

import com.enigma.parkingsystem.constant.AppPath;
import com.enigma.parkingsystem.model.entity.ParkingSpot;
import com.enigma.parkingsystem.model.request.ParkingSpotRequest;
import com.enigma.parkingsystem.model.response.CommonResponse;
import com.enigma.parkingsystem.model.response.ParkingSpotResponse;
import com.enigma.parkingsystem.service.ParkingSpotService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppPath.PARKING_SPOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ParkingSpotRequest parkingSpotRequest){
        ParkingSpotResponse parkingSpotResponse = parkingSpotService.create(parkingSpotRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created new parking spot")
                        .data(parkingSpotResponse)
                        .build());
    }

    @GetMapping("/{spotNumber}")
    public ResponseEntity<?> getParkingSpotByNumber(@PathVariable String spotNumber){
        ParkingSpotResponse parkingSpotResponse = parkingSpotService.findBySpotNumber(spotNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get parking spot "+ spotNumber)
                        .data(parkingSpotResponse)
                        .build());
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableSpots(){
        List<ParkingSpotResponse> parkingSpotResponses = parkingSpotService.findAvailableSpots();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all available parking spot")
                        .data(parkingSpotResponses)
                        .build());
    }
}
