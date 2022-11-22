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
            return ResponseEntity.status(HttpStatus.CONFLICT).body("요청하신 " + customer.getEmail()+"은 이미 존재합니다.");
        }
        CustomerEntity addedCustomer = customerService.insertCustomer(customer);
        return ResponseEntity.ok(addedCustomer);
    }

    @GetMapping
    public List<CustomerEntity> getCustomers() throws Exception {
        return customerService.selectCustomers();
    }

    @GetMapping("/{emailAddr}")  //http://localhost:8080/customers/aa@abc.com/
    public ResponseEntity<?> getCustomer(@PathVariable("emailAddr") String email) throws Exception {
        Optional<CustomerEntity> optional = customerService.selectCustomerByEmail(email);
        if(!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청하신 " + email+"이 존재하지 않습니다.");
        }
        CustomerEntity existCustomer = optional.get();
        return ResponseEntity.ok(existCustomer);
    }

    @PatchMapping
    public CustomerEntity modifyCustomer(@RequestBody CustomerEntity customer) throws Exception{
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> removeCustomer(@PathVariable String email) throws Exception {
        customerService.deleteCustomer(email);
        return ResponseEntity.ok("요청하신 " + email + "이 삭제처리 되었습니다.");
    }
}
