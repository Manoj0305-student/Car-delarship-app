package com.vw.controller;

import com.vw.dto.CustomerDto;
import com.vw.service.ExecutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/executives")
public class ExecutiveController {

    @Autowired
    private ExecutiveService executiveService;

    // Create a new customer
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = executiveService.createCustomer(customerDto);
        return ResponseEntity.ok(createdCustomer);
    }

    // Update an existing customer
    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable int id, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = executiveService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Assign a customer to an executive
    @PutMapping("/{executiveId}/customers/{customerId}")
    public ResponseEntity<Void> assignCustomerToExecutive(@PathVariable int executiveId, @PathVariable int customerId) {
        executiveService.assignCustomerToExecutive(executiveId, customerId);
        return ResponseEntity.ok().build();
    }

    // Get all customers assigned to an executive
    @GetMapping("/{executiveId}/customers")
    public ResponseEntity<List<CustomerDto>> getCustomersByExecutive(@PathVariable int executiveId) {
        List<CustomerDto> customers = executiveService.getCustomersByExecutive(executiveId);
        return ResponseEntity.ok(customers);
    }

    // Get customer by ID
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int id) {
        CustomerDto customer = executiveService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    // Get all customers
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = executiveService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Delete a customer
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        executiveService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
