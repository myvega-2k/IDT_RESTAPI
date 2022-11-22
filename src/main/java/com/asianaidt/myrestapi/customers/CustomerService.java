package com.asianaidt.myrestapi.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //customerService
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerEntity insertCustomer(CustomerEntity entity) {
        return customerRepository.save(entity);
    }



}
