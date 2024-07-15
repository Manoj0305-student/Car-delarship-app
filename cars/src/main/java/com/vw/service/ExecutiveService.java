package com.vw.service;

import com.vw.dto.CustomerDto;
import com.vw.entities.Customer;
import com.vw.entities.Executive;
import com.vw.exceptions.CustomerException;
import com.vw.exceptions.ExecutiveException;
import com.vw.repo.CustomerRepo;
import com.vw.repo.ExecutiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExecutiveService {

    @Autowired
    private ExecutiveRepo executiveRepository;

    @Autowired
    private CustomerRepo customerRepository;

    public ExecutiveService(ExecutiveRepo executiveRepository, CustomerRepo customerRepository) {
        this.executiveRepository = executiveRepository;
        this.customerRepository = customerRepository;
    }

    private CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        customerDto.setDlNumber(customer.getDlNumber());
        customerDto.setExecutiveId(customer.getExecutive().getExecutiveId());
        return customerDto;
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setDlNumber(customerDto.getDlNumber());
        Executive executive = executiveRepository.findById(customerDto.getExecutiveId())
                .orElseThrow(() -> new ExecutiveException("Executive not found with id: " + customerDto.getExecutiveId()));
        customer.setExecutive(executive);
        return customer;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDto(savedCustomer);
    }
    public CustomerDto updateCustomer(int id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerException("Customer not found with id: " + id));

        existingCustomer.setName(customerDto.getName());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setPhone(customerDto.getPhone());
        existingCustomer.setDlNumber(customerDto.getDlNumber());

        Executive executive = executiveRepository.findById(customerDto.getExecutiveId())
                .orElseThrow(() -> new ExecutiveException("Executive not found with id: " + customerDto.getExecutiveId()));
        existingCustomer.setExecutive(executive);

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return convertToDto(updatedCustomer);
    }

    //Method to assign a customer to an executive
    public void assignCustomerToExecutive(int executiveId, int customerId) {
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(() -> new ExecutiveException("Executive not found with id: " + executiveId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException("Customer not found with id: " + customerId));

        customer.setExecutive(executive);
        customerRepository.save(customer);
    }

    // Method to get all customers assigned to an executive
    public List<CustomerDto> getCustomersByExecutive(int executiveId) {
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(() -> new ExecutiveException("Executive not found with id: " + executiveId));

        List<Customer> customers = customerRepository.findByExecutive(executive);
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerException("Customer not found with id: " + id));
        return convertToDto(customer);
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteCustomer(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }

}

