package com.enigma.parkingsystem.controller;

import com.enigma.parkingsystem.constant.AppPath;
import com.enigma.parkingsystem.model.request.CarRequest;
import com.enigma.parkingsystem.model.response.CommonResponse;
import com.enigma.parkingsystem.model.response.CarResponse;
import com.enigma.parkingsystem.service.CarService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppPath.CAR)
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class CarController {

    private final CarService vehicleService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CarRequest vehicleRequest){
        CarResponse vehicleResponse = vehicleService.create(vehicleRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully created new car")
                        .data(vehicleResponse)
                        .build());
    }

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id){

        CarResponse vehicleResponse = vehicleService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get car "+ id)
                        .data(vehicleResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<CarResponse> vehicleResponses = vehicleService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all car")
                        .data(vehicleResponses)
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CarRequest vehicleRequest){
        CarResponse vehicleResponse = vehicleService.update(vehicleRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully updated car")
                        .data(vehicleResponse)
                        .build());
    }

    @DeleteMapping(AppPath.DELETED_BY_ID)
    public ResponseEntity<String> deleteById(@PathVariable String id){
        vehicleService.delete(id);
        return ResponseEntity.ok("Car with ID " + id + " deleted successfully.");
    }
}
