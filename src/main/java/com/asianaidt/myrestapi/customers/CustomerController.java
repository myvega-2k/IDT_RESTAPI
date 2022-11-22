package com.asianaidt.myrestapi.customers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Resource(name = "customerService")
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody CustomerEntity customer)
            throws Exception {
        Optional<CustomerEntity> optional = customerService.selectCustomerByEmail(customer.getEmail());
        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("요청하신 " + customer.getEmail()+"이미 존재합니다.");
        }
        CustomerEntity addedCustomer = customerService.insertCustomer(customer);
        return ResponseEntity.ok(addedCustomer);
    }

    @GetMapping
    public List<CustomerEntity> getCustomers() throws Exception {
        return customerService.selectCustomers();
    }

}
