package com.enigma.parkingsystem.service;

import com.enigma.parkingsystem.model.entity.Customer;
import com.enigma.parkingsystem.model.request.CustomerRequest;
import com.enigma.parkingsystem.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getByIdForCar(String id);

    CustomerResponse getById(String id);
    List<CustomerResponse> getAll();
    CustomerResponse update(CustomerRequest customerRequest);
    void delete(String id);
}
