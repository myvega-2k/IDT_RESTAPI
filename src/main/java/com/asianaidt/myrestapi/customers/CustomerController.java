package com.asianaidt.myrestapi.customers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Resource(name = "customerService")
    private CustomerService customerService;

    @PostMapping
    public CustomerEntity addCustomer(@RequestBody CustomerEntity customer) {
        return customerService.insertCustomer(customer);
    }
}
