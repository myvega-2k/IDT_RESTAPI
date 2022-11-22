package com.asianaidt.myrestapi.customers;

import com.asianaidt.myrestapi.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service //customerService
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerEntity insertCustomer(CustomerEntity entity) throws Exception {
        return customerRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<CustomerEntity> selectCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<CustomerEntity> selectCustomerByEmail(String email) throws Exception {
        return customerRepository.findByEmail(email);
    }

    public CustomerEntity updateCustomer(CustomerEntity entity) throws Exception {
        Optional<CustomerEntity> optional = customerRepository.findByEmail(entity.getEmail());
        CustomerEntity existCustomer =
                optional.orElseThrow(() -> new ResourceNotFoundException("CustomerEntity","email",entity.getEmail()));
        //Entity 수정
        existCustomer.setName(entity.getName());
        existCustomer.setAge(entity.getAge());
        return existCustomer;
    }




}
