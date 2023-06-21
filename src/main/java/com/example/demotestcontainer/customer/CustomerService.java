package com.example.demotestcontainer.customer;

import java.util.List;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return Streamable.of(customerRepository.findAll()).toList();
    }

    public Customer saveNewCustomer(String firstName, String lastName) {
        return customerRepository.save(new Customer(firstName, lastName));
    }
}
