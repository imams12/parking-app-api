package com.enigma.parkingsystem.service.impl;

import com.enigma.parkingsystem.model.entity.Customer;
import com.enigma.parkingsystem.model.request.CustomerRequest;
import com.enigma.parkingsystem.model.response.CustomerResponse;
import com.enigma.parkingsystem.repository.CustomerRepository;
import com.enigma.parkingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getByIdForCar(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerResponse getById(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return toCustomerResponse(customer);
    }

    private CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .build();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(this::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CustomerResponse update(CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(customerRequest.getId()).orElse(null);
        if (existingCustomer != null){
            Customer saveCustomer = Customer.builder()
                    .id(existingCustomer.getId())
                    .name(existingCustomer.getName())
                    .address(existingCustomer.getAddress())
                    .phone(existingCustomer.getPhone())
                    .user(existingCustomer.getUser())
                    .build();
            customerRepository.save(saveCustomer);

            return toCustomerResponse(saveCustomer);
        }
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(String id) {
        Customer customerToDelete = customerRepository.findById(id).orElse(null);
        customerRepository.delete(customerToDelete);
    }
}
