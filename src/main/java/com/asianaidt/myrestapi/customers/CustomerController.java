package com.asianaidt.myrestapi.customers;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Resource(name = "customerService")
    private CustomerService customerService;

    @PostMapping
    public CustomerEntity addCustomer(@RequestBody CustomerEntity customer)
            throws Exception {

        return customerService.insertCustomer(customer);
    }

    @GetMapping
    public List<CustomerEntity> getCustomers() throws Exception {
        return customerService.selectCustomers();
    }

}
