package com.example.demotestcontainer.customer;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getFirstName(), customer.getLastName());
    }

    public List<CustomerResponse> toCustomerResponse(List<Customer> customer) {
        return customer.stream().map(c -> toCustomerResponse(c)).toList();
    }
}
