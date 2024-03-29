package com.enigma.parkingsystem.controller;

import com.enigma.parkingsystem.constant.AppPath;
import com.enigma.parkingsystem.model.request.CustomerRequest;
import com.enigma.parkingsystem.model.response.CommonResponse;
import com.enigma.parkingsystem.model.response.CustomerResponse;
import com.enigma.parkingsystem.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppPath.CUSTOMER)
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id){

        CustomerResponse customerResponse = customerService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get customer with id "+ id)
                        .data(customerResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(){

        List<CustomerResponse> customerResponses = customerService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all customer")
                        .data(customerResponses)
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CustomerRequest customerRequest){

        CustomerResponse customerResponse = customerService.update(customerRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated customer with id "+customerRequest.getId())
                        .data(customerResponse)
                        .build());
    }

@DeleteMapping(AppPath.DELETED_BY_ID)
    public ResponseEntity<?> delete(@PathVariable String id){

        customerService.delete(id);
        return ResponseEntity.ok("Successfully deleted customer with id "+id);
    }
}
