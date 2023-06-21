package com.example.demotestcontainer.customer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private CustomerService customerService;
    private CustomerMapper mapper;
    private Faker faker;

    public CustomerController(CustomerService customerService, CustomerMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
        faker = new Faker();
    }

    @GetMapping("/")
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach(c -> logger.info("Found a customer: {}", c));
        return mapper.toCustomerResponse(customers);
    }

    @GetMapping("/createFakerCustomer")
    public ResponseEntity<CustomerResponse> getCreateFakerCustomer() {
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton

        Customer savedCustomer = customerService.saveNewCustomer(firstName, lastName);
        logger.info("Save new customer: {}", savedCustomer);
        return ResponseEntity.ok(mapper.toCustomerResponse(savedCustomer));
    }

}