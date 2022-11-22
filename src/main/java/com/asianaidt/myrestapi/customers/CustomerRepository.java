package com.asianaidt.myrestapi.customers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    Optional<CustomerEntity> findByEmail(String email);
    List<CustomerEntity> findByName(String name);
}
