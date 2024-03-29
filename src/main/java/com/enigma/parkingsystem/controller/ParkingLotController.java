package com.enigma.parkingsystem.controller;

import com.enigma.parkingsystem.constant.AppPath;
import com.enigma.parkingsystem.model.request.CarRequest;
import com.enigma.parkingsystem.model.request.ParkingLotRequest;
import com.enigma.parkingsystem.model.response.CarResponse;
import com.enigma.parkingsystem.model.response.CommonResponse;
import com.enigma.parkingsystem.model.response.ParkingLotResponse;
import com.enigma.parkingsystem.service.CarService;
import com.enigma.parkingsystem.service.ParkingLotService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppPath.PARKING_LOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ParkingLotRequest parkingLotRequest){
        ParkingLotResponse parkingLotResponse = parkingLotService.create(parkingLotRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created new parking lot")
                        .data(parkingLotResponse)
                        .build());
    }

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id){

        ParkingLotResponse parkingLotResponse = parkingLotService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get parking lot "+ id)
                        .data(parkingLotResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllVehicle(){
        List<ParkingLotResponse> parkingLotResponses = parkingLotService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all parking lot")
                        .data(parkingLotResponses)
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ParkingLotRequest parkingLotRequest){
        ParkingLotResponse parkingLotResponse = parkingLotService.update(parkingLotRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully updated parking lot")
                        .data(parkingLotResponse)
                        .build());
    }

    @DeleteMapping(AppPath.DELETED_BY_ID)
    public ResponseEntity<String> deleteById(@PathVariable String id){
        parkingLotService.delete(id);
        return ResponseEntity.ok("Parking Lot with ID " + id + " deleted successfully.");
    }
}
